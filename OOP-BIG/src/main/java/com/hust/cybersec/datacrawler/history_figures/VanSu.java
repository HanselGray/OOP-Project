package com.hust.cybersec.datacrawler.history_figures;

import com.hust.cybersec.datacrawler.basic_data_crawler.BasicDataCrawler;
import com.hust.cybersec.objects.Dynasty;
import com.hust.cybersec.objects.Figure;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VanSu extends BasicDataCrawler {

    private Figure figure;

    public VanSu(String url) {
        this.url = url;
        connect();
    }

    public Figure getFigure() {
        return figure;
    }

    public void scraping() {
        Element nameDiv = this.doc.getElementsByClass("active section").first();
        String name = nameDiv.text();
        System.out.println(name);
        Element table = this.doc.select("table").first();
        Element tableBody = table.select("tbody").first();
        Elements rows = tableBody.getElementsByTag("tr");
        figure = new Figure(name);
        for (Element row : rows) {
            Elements rowData = row.select("td");
            if (rowData.size() != 1) {
                String headline = rowData.get(0).text();
                if (headline.contains("Tên khác")) { // get Ten Khac
                    figure.setOtherAliases(rowData.get(1).text());
                } else if (headline.contains("Năm sinh")) { // get Nam Sinh
                    String year = rowData.get(1).text();
                    int index = year.indexOf("-");
                    String namSinh = year.substring(0, index);
                    String namMat = year.substring(index + 1);
                    figure.setDob(namSinh);
                    figure.setDod(namMat);
                } else if (headline.contains("Tỉnh thành")) { // get Tinh thanh
                    String queQuan = rowData.get(1).text();
                    figure.setHometown(queQuan);
                } else if (headline.contains("Thời kì")) { // get Thoi ki
                    Elements br = rowData.get(1).getElementsByTag("br");
                    if (br.size() <= 1) { // trong 1 thoi ki
                        String data = rowData.get(1).text();
                        int index = data.indexOf("-");
                        int openIndex = data.indexOf("(");
                        if (openIndex != -1) {
                            String dynastyData = data.substring(index + 2, openIndex);
                            dynastyData = dynastyData.trim();

                            Dynasty dynasty = new Dynasty(dynastyData);
                            figure.getDynasty().add(dynasty);
                        } else {
                            String dynastyData = data.substring(index + 2);
                            dynastyData = dynastyData.trim();
                            Dynasty dynasty = new Dynasty(dynastyData);
                            figure.getDynasty().add(dynasty);
                        }
                    } else { // nhieu thoi ki
                        String data = rowData.get(1).html();

                        String[] dynasties = data.split("<br>");
                        for (String d : dynasties) {
                            int openIndex = d.indexOf("(");
                            int index = d.indexOf("- ");
                            if (openIndex != -1) {
                                String getDynasty = d.substring(index + 1, openIndex);
                                getDynasty = getDynasty.trim();
                                Dynasty dynasty = new Dynasty(getDynasty);
                                figure.getDynasty().add(dynasty);
                            } else {
                                String getDynasty = d.substring(index + 1);
                                getDynasty = getDynasty.trim();
                                Dynasty dynasty = new Dynasty(getDynasty);
                                figure.getDynasty().add(dynasty);
                            }
                        }
                    }
                }
            } else {
                Elements paragraphs = rowData.get(0).getElementsByTag("p");
                StringBuffer ghiChu = new StringBuffer();
                for (Element p : paragraphs) {
                    ghiChu = ghiChu.append(p.text());
                }
                figure.setNotes(ghiChu.toString());
            }
        }
    }

    public String replaceTrieuDai(String original) {
        String trieuDai = "";
        switch (original) {
            case "Bắc thuộc lần 1" ->  {
                trieuDai = trieuDai.concat("Nhà Triệu");
            }
            case "Trưng Nữ Vương" ->  {
                trieuDai = trieuDai.concat("Hai Bà Trưng");
            }
            case "Nhà Tiền Lý, Triệu" ->  {
                trieuDai = trieuDai.concat("Nhà Tiền Lý");
            }
            case "Hậu Trần" ->  {
                trieuDai = trieuDai.concat("Nhà Hậu Trần");
            }
            case "Trịnh - Nguyễn" ->  {
                trieuDai = trieuDai.concat("Nhà Hậu Lê");
            }
            case "Triều Lê Sơ" ->  {
                trieuDai = trieuDai.concat("Nhà Lê sơ");
            }
            case "Nam - Bắc Triều" ->  {
                trieuDai = trieuDai.concat("Nhà Mạc");
            }
            case "Nhà Nguyễn độc lập" ->  {
                trieuDai = trieuDai.concat("Nhà Nguyễn");
            }
            case "Pháp đô hộ" ->  {
                trieuDai = trieuDai.concat("Đế quốc Việt Nam");
            }
            case "Nước Việt Nam mới" ->  {
                trieuDai = trieuDai.concat("Việt Nam Dân chủ Cộng hòa");
            }
            default ->  {
                trieuDai = trieuDai.concat("Hồng Bàng thị");
            }
        }
        return trieuDai;
    }
}
