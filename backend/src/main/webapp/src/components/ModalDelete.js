import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

export default function Delete() {

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    return (
        <>
            <Button className='btn btn-danger' onClick={handleShow}>
                Deletar
            </Button>

            <Modal show={show} onHide={handleClose} backdrop="static" keyboard={false}>
                <Modal.Header>
                    <Modal.Title className='text-secondary'>Deletar?</Modal.Title>
                </Modal.Header>
                <Modal.Body className='text-secondary'>
                    Você tem certeza que deseja deletar?
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Cancelar
                    </Button>
                    <Button variant="primary">Deletar</Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}