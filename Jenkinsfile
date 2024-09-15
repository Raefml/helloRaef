pipeline {
    agent any
    environment {
        REGISTRY = 'docker.io/raefml'
        IMAGE_NAME = 'helloraef'
        DOCKER_CREDENTIALS_ID = 'd003cd96-ecca-46b5-8fde-7fc15cbef410'
    }
    stages {
        stage('Checkout') {
            steps {
        git credentialsId: 'github-Credentials', url: 'https://github.com/Raefml/helloRaef.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
   
     
    }
}
