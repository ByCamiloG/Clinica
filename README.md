# Parcial 2: Sistema de Gestión Clínica con Login, Serialización y Manejo de Archivos

Este proyecto en Java implementa un sistema básico de gestión clínica que permite el registro de pacientes y médicos, la asignación de consultas médicas, la visualización de historial clínico, y el control de acceso mediante login con roles.
El sistema hace uso de la interfaz Serializable, archivos .ser y .txt, y excepciones personalizadas.

# Estructura del Proyecto

El proyecto está organizado en varios paquetes según la responsabilidad:

- Model: Contiene las clases Clinica, Paciente, Medico, Consulta, Usuario, y GestorUsuarios.

- View: Contiene las interfaces gráficas con Swing, como LoginFrame, VentanaPrincipal, PanelRegistro, PanelConsulta, y PanelHistorial.

- ViewModel: Clase ClinicaViewModel, puente entre el modelo y la vista.

- Persistencia: Contiene IPersistencia y su implementación PersistenciaArchivo, que maneja la serialización y persistencia en archivos de texto.

- excepciones: Define errores específicos mediante clases como CampoVacioException y UsuarioNoEncontradoException.

 # Componentes Principales
 
 1. Sistema de Login (LoginFrame)

- Permite el acceso al sistema con autenticación por usuario y contraseña.
- Soporta roles (admin, medico, administrativo) que controlan el acceso a funciones específicas.
- Permite registrar nuevos usuarios con rol definido.
- Lanza excepciones personalizadas si hay campos vacíos o credenciales incorrectas.

2. Clase Clinica

- Implementa Serializable para permitir su persistencia.
- Almacena listas de Paciente, Medico y Consulta.
- Proporciona métodos para agregar registros y recuperar historial.

3. Clase PersistenciaArchivo

- Implementa la interfaz IPersistencia para manejar la persistencia de datos:

 a) Serialización de Objetos
 Guarda el estado completo de la clínica en un archivo datos.ser.
 Utiliza ObjectOutputStream para la serialización.

 b) Deserialización de Objetos
 Restaura la clínica desde datos.ser si existe.
 Utiliza ObjectInputStream.

 c) Escritura en Archivos de Texto
 datos.txt: Historial completo de todas las consultas.
 pacientes.txt: Lista legible de todos los pacientes registrados.
 medicos.txt: Lista de médicos registrados con especialidades.

4. Manejo de Excepciones Personalizadas
CampoVacioException: Lanza error si el usuario deja campos vacíos.
UsuarioNoEncontradoException: Lanza error si el usuario no existe o la contraseña no coincide.
Permite una experiencia de usuario controlada y mensajes claros.

# 📄 Archivos Generados
Durante la ejecución, el sistema genera y utiliza los siguientes archivos:

Archivo             Propósito

datos.ser	          Archivo binario con la información completa
datos.txt	          Historial de consultas médicas
pacientes.txt	      Lista legible de pacientes registrados
medicos.txt	        Lista legible de médicos y sus especialidades
usuarios.txt	      Lista de usuarios autorizados con rol incluido

# 🛡️ Manejo de Errores

El programa contempla los siguientes errores:

- IOException: errores al leer o escribir archivos.
- ClassNotFoundException: errores durante la deserialización.
- CampoVacioException: campos vacíos en login o registro.
- UsuarioNoEncontradoException: credenciales inválidas.
