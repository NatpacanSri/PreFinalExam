let express = require("express")
let app = express()
let bodyParser = require("body-parser")
let mysql = require('mysql')

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({
    extended:true
}));

app.get('/',function(req,res){
    return res.send({error:true,message:'Test api'})
})

var dbConn = mysql.createConnection({
    host:'localhost',
    user:'root',
    password:'',
    database:'iphoneshop'  // <- เปลี่ยนชื่อ dataBase ตรงนี้
})

dbConn.connect()

app.get('/getdata',function(req,res){
    dbConn.query('SELECT * FROM `order`',function(error,results,fields){
        if(error) throw error;
        return res.send(results)
    })
})

app.post('/adddata',function(req,res){
    let data = req.body
    if(!data){
        return res.status(400).send({error:true,message:"Please provide data"})
    }
    dbConn.query('INSERT INTO `order` SET ?',[data],function(error,results,fields){
        if(error) throw error
        return res.send(results)
    })
})

app.listen(3000,function(){
    console.log("Node app is running on port 3000")
})

module.exports = app
