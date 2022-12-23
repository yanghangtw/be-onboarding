FROM ubuntu:22.04

ARG GRAALVM_VERSION=java17-22.3.0

RUN apt-get update && apt-get install -y \
    curl python3-pip git build-essential libz-dev zlib1g-dev \
    && rm -rf /var/lib/apt/lists/*

RUN curl -fsSL https://get.graalvm.org/jdk -o get-jdk.sh \
    && bash get-jdk.sh graalvm-ce-${GRAALVM_VERSION}

ENV GRAALVM_HOME="//graalvm-ce-${GRAALVM_VERSION}"
ENV PATH="//graalvm-ce-${GRAALVM_VERSION}/bin:$PATH"
ENV JAVA_HOME="//graalvm-ce-${GRAALVM_VERSION}"

RUN pip install pre-commit detect-secrets
