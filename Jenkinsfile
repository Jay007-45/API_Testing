pipeline {

    agent any
    tools {
        maven '3.6.0' 
    }
    stages {
       stage('testing stage') {
             steps {
             	
           		echo "Tests wil be triggered for : ${params.env}"
                bat "mvn -Denv=${params.env} test"
        	    bat "mvn -Denv=${params.env} -Dcucumber.options=@smoke,@fast test"


        }
    }

   }

}