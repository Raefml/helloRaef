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
        stage('Docker Build and Push') {
            steps {
                script {
                    sh "docker build -t ${REGISTRY}/${IMAGE_NAME}:${env.BUILD_ID} ."
                    withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                        sh "echo '${DOCKER_HUB_PASSWORD}' | docker login -u '${DOCKER_HUB_USERNAME}' --password-stdin"
                    }
                    sh "docker push ${REGISTRY}/${IMAGE_NAME}:${env.BUILD_ID}"
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    // Déployer l'application sur AKS avec Helm
                    sh "helm upgrade --install ${IMAGE_NAME} ./my-chart --set image.repository=${REGISTRY}/${IMAGE_NAME} --set image.tag=${env.BUILD_ID}"
                }
            }
        }
    }
}
