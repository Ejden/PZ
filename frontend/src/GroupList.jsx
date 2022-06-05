import {AppShell, Button, Header, TextInput, Modal, Navbar, NumberInput, Table, Text, UnstyledButton} from "@mantine/core";
import {useState} from "react";
import {useForm} from "@mantine/form";
import axios from "axios";

const GroupList = () => {
    const [opened, setOpened] = useState(false);
    const [groups, setGroups] = useState([]);

    const header = <Header height={40} p={"sm"}>
        <span>Dziennik elektroniczny</span>
    </Header>

    const navbar = <Navbar width={{base: 150}}>
        <Navbar.Section>
            <UnstyledButton component={'a'} href="/groups">
                <Text className={"menu-item"}>Lista klas</Text>
            </UnstyledButton>
        </Navbar.Section>
    </Navbar>

    const getClasses = () => {
        axios.get('http://localhost:8080/api/course')
            .then(response => setGroups(response.data.courses));
    }

    getClasses();

    const noGroups = groups.length === 0;

    const removeGroup = (id) => {
        axios.delete('http://localhost:8080/api/course/' + id)
    }

    const content = <>
        <div className={"list-main"}>
            <span className={'group-list-title'}>Lista grup szkolnych</span>

            <div>
                <Button onClick={() => setOpened(true)}>Utwórz klase</Button>
            </div>

            {noGroups && <span>Brak klas</span>}
            {!noGroups && <div>
                <Table
                    striped
                    highlightOnHover
                    className={"group-list-table"}
                >
                    <caption>Klasy</caption>
                    <thead>
                    <tr>
                        <th className={"group-list-table-id"}>Id</th>
                        <th className={"group-list-table-name"}>Nazwa</th>
                        <th className={"group-list-table-year"}>Rocznik</th>
                        <th className={"group-list-table-modify"}>Modyfikuj</th>
                        <th className={"group-list-table-remove"}>Usuń</th>
                    </tr>
                    </thead>
                    <tbody>
                    {groups.map((group, index) => <tr key={index}>
                        <td>{group.id}</td>
                        <td>{group.name}</td>
                        <td>{group.schoolYear}</td>
                        <td><Button>Modyfikuj</Button></td>
                        <td><Button color="red" onClick={() => removeGroup(group.id)}>Usuń</Button></td>
                    </tr>)}
                    </tbody>
                </Table>
            </div>}
        </div>
    </>

    const newGroupForm = useForm({
        initialValues: {
            name: '',
            startYear: 2022
        },
        validate: {
            name: (value) => (value.trim().length > 0 ? null : 'Niepoprawna nazwa'),
            startYear: (value) => (value > 1900 ? null : 'Rocznik powinien być wyższy niż 1900')
        }
    });

    const clearAndCloseCreateModal = () => {
        newGroupForm.values.name = '';
        newGroupForm.values.startYear = 2022;
        setOpened(false);
    }

    const createClass = (data) => {
        if (data.name.trim().length !== 0 && data.startYear > 1900) {
            axios.post(
                'http://localhost:8080/api/course',
                {
                    name: data.name,
                    schoolYear: data.startYear.toString()
                }
            ).then(() => getClasses());
        }
        clearAndCloseCreateModal();
    }

    const modal = <Modal
        opened={opened}
        onClose={() => setOpened(false)}
        title="Utwórz klasę"
    >
        <div>
            <form className="create-modal-content" onSubmit={newGroupForm.onSubmit((data) => createClass(data))}>
                <TextInput
                    label="Nazwa klasy"
                    placeholder="Nazwa klasy"
                    required
                    {...newGroupForm.getInputProps('name')}
                />

                <NumberInput
                    defaultValue={2022}
                    label="Rocznik"
                    required
                    {...newGroupForm.getInputProps('startYear')}
                />

                <div className="create-modal-button-container">
                    <Button type="submit">
                        Utwórz klasę
                    </Button>
                    <Button
                        variant="outline"
                        color="red"
                        onClick={() => clearAndCloseCreateModal()}
                    >
                        Anuluj
                    </Button>
                </div>
            </form>
        </div>
    </Modal>

    return <AppShell
        fixed
        header={header}
        navbar={navbar}
        navbarOffsetBreakpoint={"sm"}
        padding={"sm"}
    >
        {content}
        {modal}
    </AppShell>
}

export {GroupList};
