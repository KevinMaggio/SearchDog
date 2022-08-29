# Search Friend

## Ques es ? 
Search Friend es una app Creada para simular la Adopcion de Perritos. 
actualmente esta <b>consumiendo la API https://dog.ceo/dog-api</b> 
en futuras versiones se planea darle un funcionamiento real 

## Descripcion
La App Cuenta con 3 Login. 
El primero seria con la validacion de Google + fireBase 
El segundo es con un BBDD NoSql (Firestore) a este le agregamos su registro con Validacion de Campos y Validacion de datos
El tercer Login es a modo invitado. el cual tiene accesos Limitados dentro de la App y no nos genera consumos de la API. ya que con Room salvamos los datos. 

Para la estructura, mantenemos el Uso de 4 activities. <B>Splash - Login - Onboarding - Home </B>
al adentrarnos en cada seccion. navegamos con Fragments. 

## Librerias
### <B>Room</B>: Para almacenar un Listado Local y no consumir llamados a la API
### <B>Retrofit</B>: Usado en el condusumo de la Api Rest
### <B>Navigation</B>: Para hacer uso del component navigation y movernos entre Fragmentos
### <B>FireStore</B>: Usado para Almacenar los nuevos Usuarios en la app
### <B>Google</B>: consumiendo el registro con la cuenta de Google
### <B>Glide</B>: para consumir las url de Imagenes y presentarlas en los ImageView
### <B>Corrutinas</B>: Facilitando el uso de Tareas en varios hilos

## Imagenes
![image](https://user-images.githubusercontent.com/86111731/186969816-bf0bb1c6-846e-4124-83af-81336fbbc8b8.png)
![image](https://user-images.githubusercontent.com/86111731/186969845-24670390-2708-404e-bbae-83b5309d7636.png)
![image](https://user-images.githubusercontent.com/86111731/186969877-39ab2fae-e460-40c7-945c-a9e8b1eb4e55.png)
![image](https://user-images.githubusercontent.com/86111731/186969950-3b2f0f60-e8d3-4fb9-bfb6-72cc465153d1.png)
![image](https://user-images.githubusercontent.com/86111731/186970006-bdaf9642-d151-4f15-a70f-79033e2ca21d.png)
![image](https://user-images.githubusercontent.com/86111731/186970041-e52ea160-ba0c-4d37-bcc0-b8356b55712c.png)
![image](https://user-images.githubusercontent.com/86111731/186970081-c2b9061e-3f2a-461e-80b7-55118551a342.png)
![image](https://user-images.githubusercontent.com/86111731/186970129-c00a5e19-f5a1-42b8-a59e-f8b5a1e2b936.png)
![image](https://user-images.githubusercontent.com/86111731/186970234-aca31846-8a35-4638-b471-9b60aef0333b.png)
![image](https://user-images.githubusercontent.com/86111731/186970265-28097786-35e0-4b75-856e-3562652bc0de.png)
![image](https://user-images.githubusercontent.com/86111731/186970308-fa6fadd0-a349-428e-94bb-a5905ab2a8e3.png)
![image](https://user-images.githubusercontent.com/86111731/186970363-4985d67d-2912-4d98-a578-1b5c023d0911.png)
