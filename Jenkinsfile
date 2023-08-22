pipeline {
    agent any
	tools{
		maven 'maven 3.9.3'
	}
	
    stages {
        stage('Example') {
            agent any
            
            steps {
                echo 'hello Unoveo '
            }
        }
		
		stage('Build') { 
            steps {
                bat 'mvn clean install test' 
            }
        }
    }
}
