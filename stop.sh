docker kill app
docker rm app
docker rmi rogerioaraujo/app
docker kill banco
docker rm banco
docker rmi rogerioaraujo/banco

# docker rmi -f $(docker image ls rogerioaraujo/* -q)
# docker kill $(docker container ls -a -q)
# docker rm banco
# docker rm app
