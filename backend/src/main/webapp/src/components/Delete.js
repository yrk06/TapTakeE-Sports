import React from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

export default function App() {
    return (
        <div style={{
            display: 'block',
            width: 700,
            padding: 30
        }}>
            <h4>Teste da merda da modal</h4>
            <Modal.Dialog>
                <Modal.Header closeButton>
                    <Modal.Title>
                        Sample Modal Heading
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>
                        This is the sample text for our Modal
                    </p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary">
                        Deletar
                    </Button>
                    <Button variant="secondary">
                        Cancelar
                    </Button>
                </Modal.Footer>
            </Modal.Dialog>
        </div>
    );
}