import { Add } from "@mui/icons-material";
import { MainLayout } from "../layouts/MainLayout";
import {
    Box,
    Typography,
    TextField,
    FormControl,
    FormControlLabel,
    RadioGroup,
    Radio,
    Button,
    Checkbox
} from '@mui/material';
import React, { useEffect, useState } from 'react';
import axios from "../axios"
import { useNavigate, Link, useLocation } from "react-router-dom";
import { Parameter, Product } from "../types";

export function NewAgreement() {
    const location = useLocation();
    const { title } = location.state || {}; // Access the title from state

    return (
        <MainLayout title={'Новый договор ' + title}>
            <Box sx={{ width: '100%', paddingBottom: '30px' }}>
                <TextField
                    label="Текстовый редактор"
                    variant="outlined"
                    fullWidth
                    type="text"
                    value={"Текстовый редактор"} // Set the value to controlled component
                    sx={{ marginBottom: '24px' }}
                />

                <Link to="/">
                    <Button
                        sx={{
                            display: 'flex',
                            alignItems: 'center',
                            borderRadius: '12px',
                            padding: '9px 12px',
                            fontSize: '14px',
                            textTransform: 'inherit',
                            marginBottom: '24px'
                        }}
                        variant="contained"
                        fullWidth
                    >
                        Загрузить файл договора
                    </Button>
                </Link>

                <Link to="/">
                    <Button
                        sx={{
                            display: 'flex',
                            alignItems: 'center',
                            borderRadius: '12px',
                            padding: '9px 12px',
                            fontSize: '14px',
                            textTransform: 'inherit',
                        }}
                        variant="contained"
                        fullWidth
                    >
                        Создать новый продукт
                    </Button>
                </Link>

            </Box>
        </MainLayout >
    )
}