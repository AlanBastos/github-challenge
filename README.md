# github-challenge

# Popular Java Repositories Viewer

Este é um aplicativo Android desenvolvido para consultar a API do GitHub e exibir os repositórios mais populares de Java. Ele fornece uma lista de repositórios, permitindo ao usuário visualizar detalhes de cada repositório e acessar a lista de pull requests associada a cada repositório.

## Funcionalidades

- **Lista de Repositórios:** Exibe os repositórios mais populares de Java, obtidos da API do GitHub. ✅
- **Paginação Infinita:** Implementa um scroll infinito na tela de lista, carregando mais resultados à medida que o usuário rola para baixo. ✅
- **Detalhes do Repositório:** Para cada repositório, exibe nome, descrição, autor, número de estrelas e número de forks. ✅
- **Lista de Pull Requests:** Ao tocar em um item da lista de repositórios, o usuário é levado à lista de pull requests associada a esse repositório. ✅
- **Detalhes do Pull Request:** Exibe nome do autor do PR, título, data e corpo do pull request. ✅

## Exemplos de Chamadas à API

- Para obter os repositórios mais populares de Java:  ✅
  `https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1`
- Para obter a lista de pull requests de um repositório específico:  ✅
  `https://api.github.com/repos/<criador>/<repositório>/pulls`

## Tecnologias e Práticas Utilizadas

- **Testes Unitários e Instrumentados:** Implementados para garantir o bom funcionamento do aplicativo. ✅
- **Boas Práticas de Acessibilidade:** Configurado para oferecer suporte à leitura de tela e melhorar a acessibilidade para usuários com deficiência visual. ✅
- **Programação Reativa (Reactive Programming):** Utilizada para lidar com streams de dados de forma eficiente e responsiva. ✅
- **Injeção de Dependências:** Utilizada para facilitar a gestão e organização das dependências do aplicativo. ✅

