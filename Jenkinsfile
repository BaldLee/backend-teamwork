pipeline {
    stages {
        stage('Build') {
            steps {
                sh "cd config && mvn -B -DskipTests clean package"
		sh "cd discovery && mvn -B -DskipTests clean package"
		sh "cd book-service && mvn -B -DskipTests clean package"
		sh "cd user-service && mvn -B -DskipTests clean package"
		sh "cd image-service && mvn -B -DskipTests clean package"
		sh "cd rating-book-service && mvn -B -DskipTests clean package"
		sh "cd gateway && mvn -B -DskipTests clean package"
            }
        }
	stage('Test') {
            steps {
                sh "cd book-service && mvn -Dspring.profiles.active=test test"
		sh "cd user-service && mvn -Dspring.profiles.active=test test"
		sh "cd image-service && mvn -Dspring.profiles.active=test test"
            }
        }
	
    }
}
