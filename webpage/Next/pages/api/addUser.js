// https://nextjs.org/docs/api-routes/introduction

export default async function handler(req, res) {
    
    const response = await fetch('http://localhost:8080/addUser', {
        method: 'POST',
        headers: {
        'Content-Type': 'application/json'
        },
        body: JSON.stringify(req.body) 
    });
    res.end()
  }