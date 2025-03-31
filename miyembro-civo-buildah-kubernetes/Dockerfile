FROM jenkins/jenkins:jdk21

# Switch to root for installation and setup
USER root

# Install Jenkins plugins using jenkins-plugin-cli with the latest versions
RUN jenkins-plugin-cli --plugins \
    github:latest \
    workflow-aggregator:latest \
    ws-cleanup:latest \
    simple-theme-plugin:latest \
    kubernetes:latest \
    pipeline-stage-view:latest \
    github-branch-source:latest \
    ssh-credentials:latest \
    credentials-binding:latest \
    git:latest \
    git-client:latest \
    trilead-api:latest

# Update package lists and install Gradle, Docker, and other utilities
RUN apt-get update && apt-get install -y \
    gradle \
    docker.io \
    gettext \
    curl \
    && rm -rf /var/lib/apt/lists/*  # Clean up to reduce image size

# Install Kubectl (latest stable release)
RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" && \
    chmod +x ./kubectl && \
    mv ./kubectl /usr/local/bin/kubectl

# # Ensure Jenkins home directory exists and has correct permissions
# RUN mkdir -p /var/jenkins_home && \
#     chown -R jenkins:jenkins /var/jenkins_home && \
#     chmod -R 755 /var/jenkins_home
#
# # Switch back to Jenkins user for security
# USER jenkins