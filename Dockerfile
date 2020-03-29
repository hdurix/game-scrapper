FROM ubuntu:18.04

RUN apt-get update \
    && apt-get install -y --no-install-recommends curl html-xml-utils gridsite-clients ca-certificates \
    && rm -rf /var/lib/apt/lists/*

COPY sid.sh .

ENTRYPOINT ["/sid.sh"]
CMD []
