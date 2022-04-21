// https://nextjs.org/docs/api-routes/introduction

export default async function handler(req, res) {
    console.log(req.body);
    await fetch('http://localhost:8080/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
            },
            body: JSON.stringify(req.body) 
    })
    .then((res) => res.json())
    .then((data) => {
        console.log(data)
        res.status(200).json(data);
    })
}
