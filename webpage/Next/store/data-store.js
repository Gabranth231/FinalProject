import { createContext, useState, useEffect } from 'react';

const initialDataState = {
    name: ""
}
const DataContext = createContext(initialDataState);


export function DataContextProvider(props) {
    const [dataObj, setDataObj] = useState(initialDataState);
    // see https://nextjs.org/docs/basic-features/data-fetching/client-side
    
    useEffect(() => {
       fetch('api/getFilesFromUser')
       .then((res) => res.json())
       .then((data) => {
            setDataObj((oldDataObj) => {
                let prevDataObj = JSON.parse(JSON.stringify(oldDataObj))
                prevDataObj.name = data.name
                return prevDataObj
            })
       })
    },[]);

    function getUser(){
        return dataObj.name
    }
    async function login(data)  {
        const response = await fetch('api/login', {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json'
            },
            body: JSON.stringify(
                {
                    name: data,
                }
            ) 
        });
        return response
    }

    const context = {
        login:login
    };

    return (
        <DataContext.Provider value={context}>
            {props.children}
        </DataContext.Provider>
    );
}

export default DataContext;