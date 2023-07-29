package com.example.clonewhatsapp

class BaseDatosMemoria {
    companion object{
        val contactos = arrayListOf<Contacto>(
            Contacto(
                "juan",
                "https://p1.pxfuel.com/preview/914/252/853/young-man-profile-man-dream-boy.jpg",
                mutableListOf<Mensajes>(
                    Mensajes("hola", true, "7:19am"),
                    Mensajes("como estas", false,"7:20am"),
                    Mensajes("bien", true,"7:20am"),
                    Mensajes("me alegro", false,"7:21am")
                )
            ),
            Contacto(
                "Laura",
                "https://i.pinimg.com/236x/7e/2e/12/7e2e129ddb43977eb8d2c9c1c9e4a6f1.jpg",
                mutableListOf<Mensajes>(
                    Mensajes("¡Hola!", true, "10:00am"),
                    Mensajes("¿Cómo estás?", false, "10:01am"),
                    Mensajes("Todo bien, gracias.", true, "10:02am"),
                    Mensajes("¿Quieres salir hoy?", false, "10:03am"),
                    Mensajes("Lo siento, no puedo. Tengo planes.", true, "10:04am"),
                    Mensajes("Está bien, será la próxima vez.", false, "10:05am")
                )
            ),
            Contacto(
                "Carlos",
                "https://i.pinimg.com/236x/e6/d7/d2/e6d7d2c2c09bfc5b1aadbedbfdfbe435.jpg",
                mutableListOf<Mensajes>(
                    Mensajes("Hey, ¿qué tal?", true, "14:30pm"),
                    Mensajes("¡Hola! Bien, gracias.", false, "14:31pm"),
                    Mensajes("¿Te gustaría ir a la fiesta el viernes?", true, "14:32pm"),
                    Mensajes("¡Claro! Suena divertido.", false, "14:33pm"),
                    Mensajes("Te veo allí entonces.", true, "14:34pm"),
                    Mensajes("Listo, hasta el viernes.", false, "14:35pm")
                )
            ),
            Contacto(
                "Ana",
                "https://p1.pxfuel.com/preview/700/52/998/girl-portrait-studio-woman-monochrome-retro.jpg",
                mutableListOf<Mensajes>(
                    Mensajes("Buenos días.", true, "08:00am"),
                    Mensajes("¡Buenos días! ¿Cómo amaneciste?", false, "08:01am"),
                    Mensajes("Muy bien, gracias.", true, "08:02am"),
                    Mensajes("¿Quieres almorzar juntas?", false, "08:03am"),
                    Mensajes("Lo siento, ya tengo planes.", true, "08:04am"),
                    Mensajes("No hay problema, será en otra ocasión.", false, "08:05am")
                )
            ),
            Contacto(
                "Pedro",
                "https://i.pinimg.com/736x/16/87/49/168749c8caa590642457bee8de645e2f.jpg",
                mutableListOf<Mensajes>(
                    Mensajes("¡Hey!", true, "16:45pm"),
                    Mensajes("¡Hola! ¿Qué tal?", false, "16:46pm"),
                    Mensajes("Bien, gracias.", true, "16:47pm"),
                    Mensajes("¿Vamos al cine?", false, "16:48pm"),
                    Mensajes("Lo siento, estoy ocupado.", true, "16:49pm"),
                    Mensajes("No hay problema, será en otro momento.", false, "16:50pm")
                )
            ),
            Contacto(
                "David",
                "https://i.pinimg.com/236x/74/29/66/74296634384f689063fdd362cb58e4d4.jpg",
                mutableListOf<Mensajes>(
                    Mensajes("¿Qué tal estuvo el concierto?", true, "22:30pm"),
                    Mensajes("¡Fue increíble!", false, "22:31pm"),
                    Mensajes("¿Cuál fue tu canción favorita?", true, "22:32pm"),
                    Mensajes("Sin duda, 'Wake Me Up'.", false, "22:33pm"),
                    Mensajes("Es una gran elección.", true, "22:34pm"),
                    Mensajes("Sí, me encantó.", false, "22:35pm")
                )
            ),
            Contacto(
                "Sofía",
                "https://i.pinimg.com/originals/f5/71/f3/f571f309313533f403130d4e2ae3603c.png",
                mutableListOf<Mensajes>(
                    Mensajes("Hola, ¿te gustaría salir a pasear?", true, "11:20am"),
                    Mensajes("¡Hola! Claro, suena bien.", false, "11:21am"),
                    Mensajes("¿A qué hora nos vemos?", true, "11:22am"),
                    Mensajes("¿Te parece a las 2pm?", false, "11:23am"),
                    Mensajes("Perfecto, nos vemos entonces.", true, "11:24am"),
                    Mensajes("Listo, hasta luego.", false, "11:25am")
                )
            ),
            Contacto(
                "Jorge",
                "https://www.publimetro.pe/resizer/kNXSkrOc8d2SMaRnB1rJ6D_Z7uw=/800x0/filters:format(png):quality(70):focal(263x124:273x134)/cloudfront-us-east-1.images.arcpublishing.com/metroworldnews/PBR6X2WICBEKRLU5SSSE3B3V24.png",
                mutableListOf<Mensajes>(
                    Mensajes("¡Feliz cumpleaños!", true, "09:00am"),
                    Mensajes("¡Gracias! Estoy emocionado.", false, "09:01am"),
                    Mensajes("Espero que tengas un gran día.", true, "09:02am"),
                    Mensajes("Gracias, será genial.", false, "09:03am"),
                    Mensajes("Celebremos por la noche.", true, "09:04am"),
                    Mensajes("Sin duda, será una fiesta increíble.", false, "09:05am")
                )
            ),
            Contacto(
                "Luis",
                "https://i.pinimg.com/736x/6e/81/b7/6e81b7cd01b20a83b6fcbe00b8ff6dd2.jpg",
                mutableListOf<Mensajes>(
                    Mensajes("¡Hola! ¿Cómo estás?", true, "15:15pm"),
                    Mensajes("¡Hola! Estoy bien, gracias.", false, "15:16pm"),
                    Mensajes("¿Vamos a la playa este fin de semana?", true, "15:17pm"),
                    Mensajes("¡Claro! Me encantaría.", false, "15:18pm"),
                    Mensajes("Entonces, será este sábado.", true, "15:19pm"),
                    Mensajes("¡Espero que haga buen tiempo!", false, "15:20pm")
                )
            ),
            Contacto(
                "Marta",
                "https://reviews.tn/wp-content/uploads/2021/05/image_20210506_154942_photo-de-profil-originale-fille.jpg",
                mutableListOf<Mensajes>(
                    Mensajes("¡Hola! ¿Cómo te va?", true, "13:30pm"),
                    Mensajes("¡Hola! Todo bien, gracias.", false, "13:31pm"),
                    Mensajes("¿Vamos al parque mañana?", true, "13:32pm"),
                    Mensajes("¡Sí! Será divertido.", false, "13:33pm"),
                    Mensajes("Nos encontramos allí.", true, "13:34pm"),
                    Mensajes("Hasta entonces.", false, "13:35pm")
                )
            )
        )

    }
}