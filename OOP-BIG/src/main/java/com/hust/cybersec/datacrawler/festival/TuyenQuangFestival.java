package com.hust.cybersec.datacrawler.festival;

import com.hust.cybersec.objects.Festival;
import com.hust.cybersec.objects.Figure;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

public class TuyenQuangFestival extends FindFestivalAbstract {

    public TuyenQuangFestival() {
        this.url = "https://alltours.vn/tuyen-quang/cac-le-hoi-o-tuyen-quang.html";
        connect();
    }

    public void scraping() {
        Element mainContent = this.doc.getElementsByClass("single-blog-content entry clr").first();
        Elements festivalNames = mainContent.select("h2");
        Elements paragraphs = mainContent.select("p:has(br)");
        for (int i = 0; i < festivalNames.size(); i++) {
            Element p = paragraphs.get(i);
            String tenLeHoi = festivalNames.get(i).text();
            tenLeHoi = tenLeHoi.replaceAll("\\d{1,2}. ", "");
            System.out.println(tenLeHoi);
            String thoiGian = "";
            String diaDiem = "";
            String nhanVat = "";
            String noiDung = "";
            if (p != null) {
                String content = p.html();
                content = content.replace("<strong>", "");
                content = content.replace("</strong>", "");
                content = content.replace("&nbsp;", "");
                content = content.replace(":", "");
                content = content.replace("Địa điểm", "Địa điểm:");
                content = content.replace("Đối tượng suy tôn", "Đối tượng suy tôn:");
                content = content.replace("Thời gian", "Thời gian:");
                content = content.replace("Đặc điểm", "Đặc điểm:");
                content = content.trim();
                String[] data = content.split("<br>");
                for (String d : data) {
                    d = d.trim();
                    if (d.contains(":")) {
                        String[] dataParts = d.split(":");
                        String tieuDe = "";
                        String noiDungChinh = "";
                        tieuDe = tieuDe.concat(dataParts[0]);
                        noiDungChinh = noiDungChinh.concat(dataParts[1]);
                        noiDungChinh = noiDungChinh.trim();
                        // System.out.println(tieuDe);
                        // System.out.println(noiDungChinh);
                        switch (tieuDe) {
                            case "Thời gian" ->  {
                                String str = modify(tieuDe, noiDungChinh, thoiGian);
                                thoiGian = thoiGian.concat(str);
                            }
                            case "Địa điểm" ->  {
                                String str = modify(tieuDe, noiDungChinh, diaDiem);
                                diaDiem = diaDiem.concat(str);
                            }
                            case "Đối tượng suy tôn" ->  {
                                String str = modify(tieuDe, noiDungChinh, nhanVat);
                                nhanVat = nhanVat.concat(str);
                            }
                            case "Đặc điểm" ->  {
                                String str = modify(tieuDe, noiDungChinh, noiDung);
                                noiDung = noiDung.concat(str);
                            }
                        }
                    } // end small if
                } // end small for
                System.out.println(nhanVat);
                Festival festival = new Festival(tenLeHoi, thoiGian, diaDiem);
                festival.setNoiDung(noiDung);
                Figure figure = new Figure(nhanVat);
                festival.setFigure(figure);
                list.add(festival);
            } // end if
        } // end for
    }

    public String modify(String tieuDe, String noiDungChinh, String str) {
        String modifiedString = str;
        modifiedString = modifiedString.concat(noiDungChinh);
        modifiedString = modifiedString.trim();
        return modifiedString;
    }

}
