import { Box, Container, Typography } from "@mui/material"
import { Header } from "../components/Header"

interface MainLayoutProps {
  title: string | React.ReactNode
  children: React.ReactNode
}

export function MainLayout ({ title, children }: MainLayoutProps) {
  return (
    <>
      <Header />
      <Box component="main">
        <Container style={{ maxWidth: '1260px', padding: 0 }}>
          <Typography 
            display={'flex'}
            alignItems={'center'}
            lineHeight={1}
            fontWeight={600}
            fontSize={34}
            component={'h2'}
            mb={4}
          >{ title }</Typography>
          { children }
        </Container>
      </Box>
    </>
  )
}