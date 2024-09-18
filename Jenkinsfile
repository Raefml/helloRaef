pipeline {
    agent any
    environment {
        REGISTRY = 'docker.io/raefml'
        IMAGE_NAME = 'helloraef'
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
        DOCKER_TAG = "latest"
        HELM_RELEASE = "my-release"
        HELM_CHART = "my_chart"
        HELM_NAMESPACE = "default"
    }
    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'github-raef', url: 'https://github.com/Raefml/helloRaef.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${REGISTRY}/${IMAGE_NAME}:${DOCKER_TAG}")
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                        docker.image("${REGISTRY}/${IMAGE_NAME}:${DOCKER_TAG}").push("${DOCKER_TAG}")
                    }
                }
            }
        }
        stage('Deploy to k8s') {
            steps {
                script {
                    sh 'helm version'
                    sh 'helm repo update'
                    sh "helm upgrade --install ${HELM_RELEASE} ${HELM_CHART} --namespace ${HELM_NAMESPACE} --set image.repository=${REGISTRY}/${IMAGE_NAME},image.tag=${DOCKER_TAG}"
                    sh "helm status ${HELM_RELEASE} --namespace ${HELM_NAMESPACE}"
                }
            }
        }
    }
    post {
        always {
            echo 'Pipeline completed.'
        }
    }
}
