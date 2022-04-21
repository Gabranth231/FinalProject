import { useContext , useState} from "react";
import DataContext from '../store/data-store';
import { Container, Row,Input, Spacer } from '@nextui-org/react';
export default function Home() {
  const [UserName, setUserName] = useState("");
  const dataCtx = useContext(DataContext);
  const handleUserName = (event) => {
    setUserName(event.target.value);
  };
  function handler(){
    dataCtx.login(UserName);
  }
  return (
    <Container gap={0}>
      <Row gap={1}>
        <Input clearable label="Name" placeholder="Name" initialValue="" onChange={handleUserName}/> 
      </Row>
      <Spacer y={1.5}/>
      <Row gap={1}>
        <button shadow color="gradient" auto onClick={handler}>Login</button>
      </Row>
      
    </Container>
  );
}