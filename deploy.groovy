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
    def ref = sh(script: 'git rev-parse --short HEAD', returnStdout: true)

    sh "git push aptible ${ref}"
  }
}
