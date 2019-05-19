pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh "cd wordladder-oauth-consumer && mvn -B -DskipTests clean package"
		sh "cd wordladder-oauth-server && mvn -B -DskipTests clean package"
            }
        }
	stage('Test') {
            steps {
                sh "cd wordladder-oauth-consumer && mvn test"
            }
        }
	
    }
}
