import classes from './LoginPage.module.css';
import {useHistory} from 'react-router-dom'

function LoginPage() {
  const history = useHistory();
  const adminName = "Martin";
  const adminPassword = "2181";

  function login(){
    const userdata = {
      username: document.getElementById("username").value,
      password: document.getElementById("password").value
    };
    if(adminName === userdata.username && adminPassword === userdata.password){
      console.log(userdata.username);
      history.replace({pathname: '/AdminPage'});
    }
  }

  return(
    <div className = {classes.mainDiv}>
      <h1>The Warden File System</h1>
      <div className = {classes.inputFields}>
        <input type = "text" placeholder = "Enter Username" id = "username"/>
        <input type = "password" placeholder = "Enter Password" id = "password"/>
      </div>
      <div className = {classes.submitType}>
        <button onClick = {() => login()}>Login User</button>
        <button>Register User</button>
      </div>
    </div>
  );
}

export default LoginPage;