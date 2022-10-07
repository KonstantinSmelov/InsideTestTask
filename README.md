#InsideTestTask

#### [ссылка на текст задания](/Задание.txt)
<br />
При запуске в БД имеются два тестовых пользователя. К каждому "привязано" по 5 тестовых сообщений.

**HTTP POST эндпоинт http://localhost:8888/auth** ожидает на вход JSON с полями name и password. В случае успеха аутентификации отвечает JSON с полем token.

**HTTP POST эндпоинт http://localhost:8888/message** ожидает на вход JSON с полями name и message. В случае валидного токена, переданного в HTTP-заголовке, сохраняет значение message в БД с привязкой к пользователю. 
При этом, если значение message имеет вид history X (где X - положительное целое число), то эндпоинт отвечает JSON последними X сообщениями от пользователя. Если X больше общего кол-ва сообщения, то выдаются все сообщения.

**В корне проекта файлы curl-requests-ххх** с примерами curl-запросов для проверки работоспособности для curl в PowerShell и Terminal (отличаются типами ковычек).

**Посмотреть в БД:**<br />
http://localhost:8888/h2-console <br />
_JDBC URL_: jdbc:h2:mem:mydb<br />
User Name: sa<br />
_Password_: 123

**Docker Repository:**<br />
https://hub.docker.com/repository/docker/kostyasmelov/inside-test-task
