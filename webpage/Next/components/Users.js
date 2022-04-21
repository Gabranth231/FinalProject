import React, { useContext } from 'react'
import {Container, Row, Col, Card, Text, Spacer, Table, Modal, Input, Button} from "@nextui-org/react"
import { useState, useEffect} from 'react';
import DataContext from '../store/data-store';
function Users() 
{
  const dataCtx = useContext(DataContext);
  const [contact,setContact] = useState("");
  const [visable,setVisible] = useState(false);
  var user = dataCtx.getUser();
  var balence = dataCtx.getBalence();
  var contacts = dataCtx.getUserContacts();
  const handler = () =>{setVisible(true)}
  const handleSubmit = () => {
    var data = {
      userName: user,
      contactName: contact
    }
    dataCtx.addContact(data);
    setVisible(false)
  }
  const closeHandler = () =>{
    setVisible(false);
  }
  const handleUserName = (event) =>{
    setContact(event.target.value);
  }
  const columns = [
    {
      key: "contactName",
      label: "NAME",
    },
  ];
  const rows = [
    {
      key: "1",
      contactName: contacts,
    },
  ];
  return (
    <Container gap={0}>
      <Spacer y={2}/>
      <Row gap={1}>
        <Col>
          <Container gap={0}>
          <Row gap={1}>
            <Card color="success">
              <Text h6 size={15} color="white" css={{ m: 0 }}>
                Account Details
              </Text>
            </Card>
            </Row>
            <Spacer y={1}/>
            <Row gap={1}>
            <Card color="secondary">
              <Text h6 size={15} color="white" css={{ m: 0 }}>
                Name: {user}
              </Text>
            </Card>
            </Row>
            <Spacer y={1}/>
            <Row gap={1}>
            <Card color="secondary">
              <Text h6 size={15} color="white" css={{ m: 0 }}>
                Balence: {balence}
              </Text>
            </Card>
            </Row>
            <Spacer y={2}/>
            <Row gap={1}>
              <Card onClick={handler}>
                <Modal
                  closeButton
                  aria-aria-labelledby='modal-title'
                  open = {visable}
                  onClose = {closeHandler}
                >
                  <Modal.Header>
                    <Text id="modal-title" size={10}>
                      Add Contact
                    </Text>
                  </Modal.Header>
                  <Modal.Body>
                    <Input clearable label="Name" placeholder="Name" initialValue="" onChange={handleUserName}/> 
                  </Modal.Body>
                  <Modal.Footer>
                    <Button onClick={handleSubmit}>
                      Add
                    </Button>
                    <Button auto flat color="error" onClick={closeHandler}>
                      Close
                    </Button>
                  </Modal.Footer>
                </Modal>
              </Card>
            </Row>
          </Container>
        </Col>
        <Col>
          <Table
            aria-label="Example table with dynamic content"
            css={{
              height: "auto",
              minWidth: "100%",
            }}
          >
            <Table.Header columns={columns}>
              {(column) => (
                <Table.Column key={column.key}>{column.label}</Table.Column>
              )}
            </Table.Header>
            <Table.Body items={rows}>
              {(item) => (
                <Table.Row key={item.key}>
                  {(columnKey) => <Table.Cell>{item[columnKey]}</Table.Cell>}
                </Table.Row>
              )}
            </Table.Body>
          </Table>
        </Col>
      </Row>
    </Container>
  );
}
  
  export default Users;