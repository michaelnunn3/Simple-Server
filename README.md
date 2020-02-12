Server Program 
A  server  program  is  an  always-running  program  that  accepts  the  clients’  requests. This  program  starts 
earlier  than  all  clients  and  waits  for  the  in-coming  connection.  After  accepting  an  incoming  request 
from a client, it responds with a message “Hello!” to the client. Then whenever it receives an operation 
command  from  the  client,  it  prints  out  the  command  on  screen  and  then  returns  with  the  calculation 
result  (e.g.  for  the  message  “add  4  5”  from  client,  the  server  will  send  a  message  “9”  to  the  client). 
Maximum number of inputs following the operator should be four, and the server returns an error code 
for exceeding or insufficient number of inputs. Note that the returning message to the client should only 
include  a  number  (calculation  result  or  error  code.  If  the  server  receives  a  command  “bye”  from  a 
client, it closes the corresponding socket, but can still be communicating with other clients. However, if 
the  server  receives  command  “terminate”  from  any  client,  it  closes  all  the  sockets  and  exit.  And  all 
clients exit too. 


Client Program 
Clients are started on remote machines after a server is running. After connecting to the server and 
receiving  “Hello!”  message,  client  program  prints  it  out  and  lets  the  user  input  a  command  line. 
Then  it  will  send  the  command  to  the  server  (clients 
do  not
  handle  invalid  input)  and  receive  a 
response.  After  printing  out  the  result  or  error  message  (to  be  specified  later),  it  will  let  the  user 
input again. The following are the operation commands used in this project: 
add     number1 
number2 
... 
subtract  number1  number2  ... 
multiply number1 number2 ... 
Please note that there should be 2 to 4 input parameters after each operator. 
