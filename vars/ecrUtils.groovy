/**
 * Method to get AWS account Id
 * @return      AWS account ID
 */
def getAWSAccountId() {
    awsAccountId = sh(returnStdout: true, script: "aws sts get-caller-identity --output text| awk '{print \$1}'").trim()
    return "${awsAccountId}"
}

/**
 * Performs docker login for ECR in specified AWS regions
 *
 * @param awsAccountId  AWS Account ID
 * @param regions       List of regions, in form of ["region1","region2"]
 * @return
 */
def ecrLogin(regions = [], awsAccountId = null) {
    def account = ""
    if (awsAccountId == null) {
        account = getAWSAccountId()
    } else {
        account = awsAccountId
    }
    regions.each { region ->
        sh "aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${account}.dkr.ecr.${region}.amazonaws.com"
    }
}

/**
 * Retrieve the most recent (by the date) ECR image tag (version)
 *
 * @param image     ECR repo name
 * @param region    AWS region
 * @return          The most recent (by the date) Image tag
 */
def getLatestImageVersion(String image, String region) {
    version = sh(returnStdout: true, script:"aws ecr describe-images --region ${region} --repository-name ${image} --output text --query 'sort_by(imageDetails,& imagePushedAt)[*].imageTags[*]'| tr '\t' '\n'| sort | tail -1").trim()
    return "${version}"
}

/**
 *
 * @param image             image (ecr repository) name
 * @param dockerFile        path to Dockerfile
 * @param regions           the list of regions, in form of ["region1","region2"]
 * @param version           image version (tag)
 * @param awsAccountId      AWS account ID
 * @return
 */
def buildPublishDockerImage(String image, String dockerFile, regions = [], version = "latest", awsAccountId = null) {
    def tags = ""
    def account = ""

    if (awsAccountId == null) {
        account = getAWSAccountId()
    } else {
        account = awsAccountId
    }

    regions.each { region ->
        tags = tags + " --tag ${account}.dkr.ecr.${region}.amazonaws.com/${image}:${version}"
    }

    sh "docker build ${tags} -f ${dockerFile} ."

    regions.each { region ->
        sh "docker push ${account}.dkr.ecr.${region}.amazonaws.com/${image}:${version}"
    }
}
