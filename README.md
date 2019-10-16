# JavaPOZ16-blog
blog JAVApoz16-working-example in JSP Technology

I'm use front from template https://startbootstrap.com/themes/clean-blog/

built basic backend with:

* login logic:
-mySQL database with users
-validation if username is empty,null and is in the base, or password is correct
-use the BCrypt to hash password
-checking if user is activated
-login in the session and logging filter to use blog page

* registration logic:
-add new user and hashed password to the base
-generate token during registration and send email with it
-activate user by activation link
-checking if user is registered and show message