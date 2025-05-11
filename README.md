# AgentService

## TODOS

[ ] O serviço (que seria o client em si) deve ser capaz de criar a Task, pois apenas 

## Comunicação
Esse service deve servir para três tipos de comunicação (padrão JSON-RPC 2.0):
1. Resquest/Response
   O client envia uma request do método tasks/send e recebe uma resposta do servidor.
   Para processamentos longos o client envia uma request do método tasks/send e o servidor responde com Working status,
   para que posteriormente o client possa buscar a resposta enviando uma request do método tasks/get até que ela chegue
   no Completed status.

TODO: Não vamos implementar a camada de Interface. Então os métodos deverão estar no Service para ser possível mapear
quando o framework implementar a camada de Interface.

2. Streaming SSE (Semelhante ao Client do MCP)
   Para tarefas que produzem resultados incrementais ou fornecem atualizações de progresso em tempo real.
   O cliente inicia uma tarefa usando tasks/sendSubscribe (infelizmente não é o mesmo método do MCP tem que fazer uma camada
   de abstração caso vá expandir esse Client).
   O servidor responde com uma conexão HTTP que permanece aberta, pela qual ele envia um fluxo de eventos enviados pelo 
   servidor (SSE).
   Esses eventos podem ser TaskStatusUpdateEvent (para alterações de status) ou TaskArtifactUpdateEvent (para blocos de 
   artefatos novos ou atualizados).
   Isso requer que o servidor anuncie a capacidade de streaming no seu Cartão de Agente.
   
3. Push Notification
   Esse é o maior diferencial do protocolo. Ainda que o Client do Protocolo do MCP trabalhe com SSE, com agentes espera-se
   que as tarefas sejam maiores e mais complexas que o uso de uma tool. Sendo assim esse tipo de comunicação seria ideal.
   para o agente que será considerado o Client do usuário abordagens como essa se enquadram para aplicativos de 
   mensagem, que não faria sentido streamar a resposta e sim responder quando tiver pronto (a outra opção seria periodicamente)
   buscar no servidor a task com um tasks/get em Request/Response padrão. Do ponto de vista da rede em si, push notification
   aliviaria o tráfego.

   Para tarefas ou cenários de longa duração, onde manter uma conexão persistente (como SSE) é impraticável.
   O cliente pode fornecer uma URL de webhook ao iniciar uma tarefa (ou chamando tasks/pushNotification/set).
   Quando o status da tarefa muda significativamente (por exemplo, é concluído, falha ou requer entrada), o servidor 
   pode enviar uma notificação assíncrona (uma solicitação HTTP POST) para este webhook fornecido pelo cliente.
   Isso requer que o servidor anuncie o recurso pushNotifications no seu Cartão de Agente.

