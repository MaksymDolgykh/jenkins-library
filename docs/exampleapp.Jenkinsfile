@Library('jenkins-library@develop') _

pipeline {
    agent any

    options {
        ansiColor("xterm")
    }

    stages {
        stage('ECR login') {
            steps {
                script {
                    ecrUtils.ecrLogin(["us-east-1"])
                }
            }
        }
        stage("Build and Publish ECR Image") {
            steps {
                script {
                    logging.info("Building and Publishing example app image")
                    ecrUtils.buildPublishDockerImage("exampleapp", "./docs/exampleapp.Dockerfile", ["us-east-1"])
                }
            }
        }
    }
}
