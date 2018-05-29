node {
  checkout scm

  stage('Set up remote') {
    def remoteURL = 'git@beta.aptible.com:spark-prod/github-review-slack-notifier.git'

    try {
      sh "git remote add aptible ${remoteURL}"
    } catch (all) {
      sh "git remote set-url aptible ${remoteURL}"
    }
  }

  stage('Push to aptible') {
    sh "git checkout master"
    sh "git pull origin master"
    sh "git push aptible master"
  }
}
