# Despliegue en Render + TiDB Cloud

## 1. Variables de entorno en Render

| Variable | Valor |
|---|---|
| `DB_HOST` | Host publico de TiDB Cloud |
| `DB_PORT` | `4000` |
| `DB_NAME` | `gluco_api` |
| `DB_USER` | Usuario de TiDB Cloud |
| `DB_PASSWORD` | Contrasena de TiDB Cloud |
| `DB_SSL_MODE` | `REQUIRED` |

## 2. Generar el WAR

En NetBeans: clic derecho en el proyecto -> **Clean and Build**.

El archivo generado debe quedar en:

```
dist/API_HealthBlood_512.war
```

> Cada vez que cambies el codigo Java debes volver a hacer Clean and Build antes de subir los cambios a GitHub.

## 3. Subir a GitHub

```powershell
git init
git add .
git commit -m "Configuracion de despliegue en Render"
git branch -M main
git remote add origin https://github.com/TU_USUARIO/TU_REPO.git
git push -u origin main
```

## 4. Crear el servicio en Render

1. Entrar a https://dashboard.render.com
2. **New +** -> **Web Service**
3. Conectar el repositorio de GitHub
4. Configurar:
   - **Name:** `healthblood-api`
   - **Region:** la misma zona de TiDB (por ejemplo Virginia)
   - **Branch:** `main`
   - **Runtime:** Docker
   - **Instance Type:** Free
5. Agregar las variables de entorno de la tabla anterior.
6. **Health Check Path:** `/api/glucosa/saludar`
7. **Create Web Service**

## 5. Probar

```
https://TU-APP.onrender.com/api/glucosa/saludar
https://TU-APP.onrender.com/api/glucosa/getAllUsuarios
https://TU-APP.onrender.com/api/glucosa/getAllMedidas
```

## Notas

- El plan Free de Render duerme el servicio a los 15 minutos sin trafico. La primera peticion puede tardar cerca de un minuto.
- No subas contrasenas al repositorio. Usa las variables de entorno de Render.
- La base de datos esta en TiDB Cloud, no en Render.
