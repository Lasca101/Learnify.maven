const express = require('express');
const app = express();
const port = 6781;
const multer = require('multer'); // Para processar o upload do arquivo
const upload = multer(); // Configuração para processar o upload

app.use(express.static('public'));

// Configuração para aceitar arquivos via POST
app.post('/montar-curso', upload.single('img'), (req, res) => {
    const img = req.file; // O arquivo é acessado com req.file
    uploadFile(img).then(() => {
        res.send('Função chamada com sucesso!');
    }).catch(error => {
        console.error('Erro:', error);
        res.status(500).send('Erro ao fazer upload do arquivo.');
    });
});

app.listen(port, () => {
    console.log(`Servidor rodando em http://localhost:${port}`);
});




const fs = require('fs')
const { google } = require('googleapis')

const GOOGLE_API_FOLDER_ID = '1sUc-6jiaa-UficJwGNaUuKTvoLXLqsq2'


async function uploadFile(img) {
    try {
        const auth = new google.auth.GoogleAuth({
            keyFile: './googledrive.json',
            scopes: ['https://www.googleapis.com/auth/drive']
        });

        const driveService = google.drive({
            version: 'v3',
            auth
        });

        const fileMetaData = {
            'name': 'img_teste.jpg',
            'parents': [GOOGLE_API_FOLDER_ID]
        };

        const media = {
            mimeType: 'image/jpg',
            body: img.buffer, // Use o buffer do objeto do arquivo
        };

        const response = await driveService.files.create({
            resource: fileMetaData,
            media: media,
            fields: 'id'
        });
        return response.data.id;
    } catch (err) {
        console.log('erro criando arquivo: ', err);
    }
}

uploadFile().then(data=>{
    console.log(data)
})


