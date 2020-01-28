import React, {useState, Component} from 'react';
import html2canvas from "html2canvas";
import pdfMake from "pdfmake/build/pdfmake";
import Button from '@material-ui/core/Button';

const PdfExport = props => {

    const DOWNLOAD_FILE_NAME = "WebDesigner-Design.pdf"

    const printToPdf = () => {
        const { ids } = props;
        function nextStep(sections = [], i = 0) {
            if (i >= ids.length)  {
                let pdfExportSetting = {
                    content: sections
                };
                pdfMake.createPdf(pdfExportSetting).download(DOWNLOAD_FILE_NAME);
                return;
            }

            html2canvas(document.getElementById(ids[i])).then(canvas => {
                let data = canvas.toDataURL();
                let d = {
                    image: data,
                    width: 450
                }
                sections.push(d);
                nextStep(sections, ++i);
            });
        }
        nextStep();
    };

    return (
        <div>
            <Button onClick={printToPdf} style={{ backgroundColor: "green" }}>
            Print It
            </Button>
        </div>
    );


};

export default PdfExport;
