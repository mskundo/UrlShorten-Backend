Application is available in docker
We can run the application in 2 ways.

Way-1
________
1. Pull the docker image to local
   docker image pull mskundo/urlshorten-backend
2. Run the image in local
   docker container run -p 8080:8080 urlshorten-backend  

Way - 2
___________
1. Clone the application in local.
   git clone https://github.com/mskundo/UrlShorten-Backend.git
2. Create an image locallly
   docker image build -t urlshorten-backend .   
3. Run the image in local
   docker container run -p 8080:8080 urlshorten-backend    

