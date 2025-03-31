FROM jenkins/jenkins:slim-jdk21

USER root

# Install essential tools (Docker CLI, Buildah, Git, Sudo)
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        docker.io \
        buildah \
        podman \
        git \
        sudo \
        openssh-client \
        fuse-overlayfs && \
    rm -rf /var/lib/apt/lists/*

# Ensure docker group exists and add jenkins user to it
RUN groupadd -f docker && \
    usermod -aG docker jenkins

# Configure Buildah storage (rootless mode)
RUN mkdir -p /var/lib/containers && \
    chown -R jenkins:jenkins /var/lib/containers

# Install essential Jenkins plugins
RUN jenkins-plugin-cli --plugins \
    workflow-aggregator \
    docker-workflow \
    git \
    ssh-credentials \
    pipeline-utility-steps

# Configure permissions and Docker/Buildah access
RUN mkdir -p /var/jenkins_home && \
    chown -R jenkins:jenkins /var
