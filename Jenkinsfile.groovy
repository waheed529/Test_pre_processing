pipeline {
    agent any
    environment {
    registry = "waheed529/docker-test"
    registryCredential = "dockerhub"
   }
   
    stages {
        stage ('clean') {
            steps {
                sh "mvn clean"
                 echo 'This is a clean pipeline.'
            }
        }

        stage ('Package') {
            steps {
                sh "mvn package"
                echo 'This is a package pipeline.'
                  dockerImage = docker.build registry + ":$BUILD_NUMBER"
                 echo 'This is a image build registry.'
            }
        }
    }
}