import {AppShell, Button, Header, Navbar, Table, Text, UnstyledButton} from "@mantine/core";

const GroupList = () => {
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

    const groups = [
        {
            id: '1',
            name: '1a',
            schoolYear: '2021'
        },
        {
            id: '2',
            name: '2a',
            schoolYear: '2021'
        },
        {
            id: '3',
            name: '3a',
            schoolYear: '2021'
        }
    ]

    const noGroups = groups.length === 0;

    const content = <>
        <div className={"list-main"}>
            <span className={'group-list-title'}>Lista grup szkolnych</span>

            <div>
                <Button>Utwórz klase</Button>
            </div>

            { noGroups && <span>Brak klas</span> }
            { !noGroups && <div>
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
                            <td><Button>Usuń</Button></td>
                        </tr>)}
                    </tbody>
                </Table>
            </div> }
        </div>
    </>

    return <AppShell
        fixed
        header={header}
        navbar={navbar}
        navbarOffsetBreakpoint={"sm"}
        padding={"sm"}
    >
        {content}
    </AppShell>
}

export { GroupList };
