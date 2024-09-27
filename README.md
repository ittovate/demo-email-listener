# Email Listener Demo
- This is an Email Listener library demo which enables you to have your own filtering and processing logic over at incoming emails in your inbox folder. Currently there is a dummy filter and dummy action **explained in details in `USAGE.md`** but in order to get the most out of this library you must use your own logic to customize it.

# Prerequisites:
- Java 21
- Maven ** v3.9.6 is used here ** 
- An email provider account , e.g: Gmail

# Steps to run:
- NOTE: this demo mainly used Gmail but whatever your mail provider is you just need to get the credentials in your mail provider's way

## You must create a `.env` file inside `src/main/resources` with your credentials
1. Create a file named `.env` inside `src/main/resouces` 
2. Copy the keys in the `.env.example` into your `.env` file
3. Get your credentials from your mail provider
If your mail provider is Gmail this is the keys you need to fill
````
MAIL_HOST=imap.gmail.com
MAIL_PORT=993
MAIL_USERNAME=
MAIL_PASSWORD=
MAIL_TRANSPORT_PROTOCOL=imaps
````
- In order to get your mail username and password in Gmail for example you must create an app password ---> [How to get application credentials in Gmail](https://www.youtube.com/watch?v=74QQfPrk4vE)

- After this you are set to go and you can build and run the application using the following command 
`mvn clean package`
- This should make a new `.jar` file inside the build folder `target` so you can run it using the following command
`java -jar target/email-listener-0.0.1-SNAPSHOT.jar`
- If you are using your IDE you can just run it without this command but be sure to have Java 21 running

