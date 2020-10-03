pipeline {
    
    environment {
    registry = "waheed529/docker-test"
    registryCredential = "dockerhub"
   }
   agent any
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
                
            }
        }
    }
}