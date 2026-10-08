CREATE DATABASE IF NOT EXISTS unitalent_auth;
CREATE DATABASE IF NOT EXISTS unitalent_estudantes;
CREATE DATABASE IF NOT EXISTS unitalent_empresas;
CREATE DATABASE IF NOT EXISTS unitalent_vagas;
CREATE DATABASE IF NOT EXISTS unitalent_candidaturas;
CREATE DATABASE IF NOT EXISTS unitalent_notificacoes;

GRANT ALL PRIVILEGES ON unitalent_auth.* TO 'unitalent'@'%';
GRANT ALL PRIVILEGES ON unitalent_estudantes.* TO 'unitalent'@'%';
GRANT ALL PRIVILEGES ON unitalent_empresas.* TO 'unitalent'@'%';
GRANT ALL PRIVILEGES ON unitalent_vagas.* TO 'unitalent'@'%';
GRANT ALL PRIVILEGES ON unitalent_candidaturas.* TO 'unitalent'@'%';
GRANT ALL PRIVILEGES ON unitalent_notificacoes.* TO 'unitalent'@'%';
FLUSH PRIVILEGES;
