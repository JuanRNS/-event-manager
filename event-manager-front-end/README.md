# Party House System

Sistema de gerenciamento de festas e eventos desenvolvido em Angular 20 com TypeScript.

## 🎯 Funcionalidades

### 1. Tela de Login e Cadastro
- Autenticação com email e senha
- Login social (Google e Apple)
- Interface responsiva com sidebar informativo

### 2. Cadastro de Festas no Sistema
- Formulário para lançamento de trabalhos
- Campos: Local da festa, Nome do cliente, Endereço, Material usado, Valor da festa
- Lista de festas cadastradas
- Funcionalidade para adicionar garçons às festas

### 3. Cadastro de Componentes do Evento
- **Cadastro de Garçons**: Nome, Telefone, Chave PIX, Status
- **Cadastro de Materiais**: Descrição do material
- Lista de garçons cadastrados com opção de edição
- Lista de materiais disponíveis

### 4. Dashboard das Informações
- Cards informativos de cada garçom
- Métricas: festas na semana e total a receber
- Resumo geral: Total de garçons, Total de festas, Total pago
- Botão para gerar relatórios

## 🎨 Design System

### Cores
- **Primária**: #4ECDC4 (Verde/Turquesa) - Botões de ação
- **Secundária**: #2C3E50 (Cinza escuro) - Menu lateral
- **Fundo**: #F8F9FA (Cinza claro)
- **Texto**: #2C3E50 (Cinza escuro)
- **Branco**: #FFFFFF

### Tipografia
- Fonte: Inter (Google Fonts)
- Pesos: 300, 400, 500, 600, 700

## 🚀 Como executar o projeto

### Pré-requisitos
- Node.js 18+ 
- Angular CLI 20+

### Instalação
```bash
# Clone o repositório
git clone <url-do-repositorio>

# Entre no diretório
cd party-house-system

# Instale as dependências
npm install

# Execute o projeto
ng serve
```

### Acesso
- Aplicação: http://localhost:4200
- Login: Qualquer email e senha válidos

## 📱 Responsividade

O sistema foi desenvolvido com design responsivo, adaptando-se a:
- Desktop (1200px+)
- Tablet (768px - 1199px)
- Mobile (até 767px)

## 🏗️ Arquitetura

### Estrutura de Componentes
```
src/app/components/
├── login/                 # Tela de login e cadastro
├── party-registration/    # Cadastro de festas
├── event-components/      # Cadastro de garçons e materiais
├── dashboard/            # Dashboard principal
└── sidebar/              # Menu lateral compartilhado
```

### Tecnologias Utilizadas
- **Angular 20**: Framework principal
- **TypeScript**: Linguagem de programação
- **SCSS**: Pré-processador CSS
- **Angular Router**: Navegação entre páginas
- **Angular Forms**: Formulários reativos
- **Angular Material**: Componentes UI (opcional)

## 🔧 Funcionalidades Técnicas

### Roteamento
- Rota padrão: `/login`
- Rotas protegidas (simulação)
- Navegação por menu lateral

### Formulários
- Validação de campos obrigatórios
- Feedback visual para o usuário
- Reset automático após submissão

### Estado da Aplicação
- Dados mockados para demonstração
- Estrutura preparada para integração com backend
- Interfaces TypeScript para tipagem

## 🧪 Testes

### Executar testes
```bash
# Testes unitários
ng test

# Testes e2e
ng e2e
```

## 📦 Build para Produção

```bash
# Build otimizado
ng build --prod

# Os arquivos serão gerados em dist/party-house-system/
```

## 🔒 Segurança

### Implementações de Segurança
- Sanitização de inputs
- Validação client-side
- Estrutura preparada para autenticação JWT
- Headers de segurança configurados

### Próximos Passos de Segurança
- Implementar autenticação real
- Adicionar autorização por roles
- Configurar HTTPS
- Implementar rate limiting

## ♿ Acessibilidade

### Recursos Implementados
- Estrutura semântica HTML5
- Labels apropriados em formulários
- Contraste adequado de cores
- Navegação por teclado
- Textos alternativos

### Conformidade WCAG 2.1
- Nível AA de acessibilidade
- Testado com leitores de tela
- Suporte a navegação por teclado

## 📊 Performance

### Otimizações
- Lazy loading de componentes
- OnPush change detection strategy
- Minificação de assets
- Tree shaking automático
- Compressão gzip

## 🤝 Contribuição

### Padrões de Código
- ESLint configurado
- Prettier para formatação
- Conventional Commits
- Testes obrigatórios para novas features

### Workflow de Desenvolvimento
1. Fork do projeto
2. Criar branch feature
3. Implementar mudanças
4. Executar testes
5. Criar Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 👥 Equipe de Desenvolvimento

- **Tech Leader**: Coordenação e arquitetura
- **Frontend Developer**: Implementação Angular/TypeScript
- **UX/UI Designer**: Design e experiência do usuário
- **QA Engineer**: Testes e qualidade
- **Security Specialist**: Segurança e vulnerabilidades
- **Accessibility Expert**: Conformidade WCAG

## 📞 Suporte

Para dúvidas ou suporte, entre em contato:
- Email: suporte@partyhouse.com
- Documentação: [Link da documentação]
- Issues: [Link do GitHub Issues]

---

**Desenvolvido com ❤️ pela equipe Party House**

