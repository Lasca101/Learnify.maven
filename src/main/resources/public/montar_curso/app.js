const fs = require('fs')
const { google } = require('googleapis')

const GOOGLE_API_FOLDER_ID = '1sUc-6jiaa-UficJwGNaUuKTvoLXLqsq2'


async function uploadFile(){
    try {
        const auth = new google.auth.GoogleAuth({
            keyFile: './googledrive.json',
            scopes: ['https://www.googleapis.com/auth/drive']
        })

        const driveService = google.drive({
            version: 'v3',
            auth
        })

        const fileMetaData = {
            'name': id,
            'parents': [GOOGLE_API_FOLDER_ID]
        }

        const media = {
            mimeType: 'image/jpg',
            body: fs.createReadStream('./img.jpg')
        }

        const response = await driveService.files.create({
            resource: fileMetaData,
            media: media,
            fields: 'id'
        })
        return response.data.id
    } catch (err) {
        console.log('erro criando arquivo: ', err)
    }
}

uploadFile().then(data=>{
    console.log(data)
})