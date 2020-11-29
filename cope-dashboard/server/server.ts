const express = require('express');
const morgan = require('morgan');

const app = express();
const port = process.env.PORT || 8080;

app.use(morgan('tiny'));
app.use(express.static(__dirname + '/dist/cope-dashboard/'));

// For virtual routes use
app.use('*', (req, res) => {
    res.sendFile(__dirname + '/dist/cope-dashboard/index.html');
});


app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
})
