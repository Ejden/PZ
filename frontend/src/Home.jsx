import { AppShell, Header, Navbar, Text, UnstyledButton } from "@mantine/core";
import "./App.css"

function Home() {
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

    const content = <span>cokolwiek</span>

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

export { Home };