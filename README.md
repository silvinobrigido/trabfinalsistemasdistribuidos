# trabfinalsistemasdistribuidos

Para compilar o projeto é necessário  abrir o cmd e rodar o seguinte comando na pasta microservicos:
./mvnw clean package '-Dmaven.test.skip=true' 

Para compilar os fontes na imagem do docker,  rodar o seguinte comando na pasta docker: docker-compose -f docker-compose.yml build

Para executar certifique-se q o docker está em execução, rodar o seguinte comando na pasta docker: docker-compose -f docker-compose.yml up -d

