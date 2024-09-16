pipeline {
    agent any
    environment {
        REGISTRY = 'docker.io/raefml'
        IMAGE_NAME = 'helloraef'
    }
    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'github-raef', url: 'https://github.com/Raefml/helloRaef.git'
            }
        }
        stage('Build') {
            steps {
                
                sh 'mvn --version'
               
                sh 'mvn clean package'
            }
        }
        stage('Docker Build and Push') {
            steps {
                script {
               
                    sh "docker build -t ${REGISTRY}/${IMAGE_NAME}:${env.BUILD_ID} ."
               
                  withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                        sh "echo '${DOCKER_HUB_PASSWORD}' | docker login -u '${DOCKER_HUB_USERNAME}' --password-stdin"
                }

               
                    sh "docker push ${REGISTRY}/${IMAGE_NAME}:${env.BUILD_ID}"
                }
            }
        }
    }
}
