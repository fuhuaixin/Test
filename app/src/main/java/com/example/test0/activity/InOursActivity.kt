package com.example.test0.activity

import android.content.Intent
import android.view.View
import com.example.test0.R
import com.example.test0.base.BaseActivity
import kotlinx.android.synthetic.main.activity_in_ours.*
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 * 走进我们页面
 */
class InOursActivity : BaseActivity(), View.OnClickListener {

    private var strPath = "";
    var sendPath: String = ""
    var sendTitle: String = ""
    var sendContent: String = ""
    //街道简介url
    var introUrl: String = "http://www.jinshui.gov.cn/jsjj.jhtml"
    //地理位置url
    var locationUrl: String = "http://www.jinshui.gov.cn/dlwz.jhtml"
    //行政区划url
    var areaUrl: String = "http://www.jinshui.gov.cn/xzqh.jhtml"
    //资源设施url
    var energyUrl: String = "http://www.jinshui.gov.cn/zyss.jhtml"

    override fun setLayoutId(): Int {
        return R.layout.activity_in_ours

    }

    override fun initView() {
        strPath = intent.getStringExtra("path")
        tvPath.text = strPath
        tvIntro.setOnClickListener(this)
        tvLocation.setOnClickListener(this)
        tvArea.setOnClickListener(this)
        tvEnergy.setOnClickListener(this)
    }

    override fun initListener() {

        llBack.setOnClickListener {
            finish()
        }

    }

    override fun onClick(v: View?) {
        var intent = Intent(this, InOursMesActivity::class.java)
        when (v?.id) {

            R.id.tvIntro -> {
                sendPath = "$strPath/街道简介"
                sendTitle = "街道简介"
                sendContent =
                    "\t英协社区现有工作人员8人，共有常住住户512户，总人数1100余人，流动人口430余人，辖区共有私营企业及个体商户56户。\n\n" +
                            "\t社区建设新颖、布局合理、环境优美，是集别墅、多层、高层于一身的智能化高尚住宅小区，有64幢独立别墅、7幢公寓式别墅、19幢多层公寓楼、7幢高层，其中4幢商务楼。目前整体绿化面积近20万平方米，绿化率达到58%，花草树木10万棵以上，树木覆盖面积30000平方米，地面硬化率达到了100%，2008年我社区被省园林局评为省级绿色社区称号。\n\n" +
                            "\t长期以来，英协社区居委会在凤凰台街道党工委、街道办事处及辖区广大居民的的正确指导和大力支持下，社区全体工作人员团结一致，各尽职责，以服务居民为载体，以“诚实守信，共筑共建”为主题，以“因地制宜，改革创新，注重实效”为方针，以创建文明社区为目标, 坚持“以人为本，突出特色”的原则，不断挖掘社区发展潜力，加强领导，严格管理，各项工作都取得了不断进步。社区环境不断改善，社会治安秩序日趋稳定，居民思想、文化、身体素质不断提高，家庭邻里人际关系日益和谐，百姓安居乐业。\n\n" +
                            "\t今后，社区将进一步加大对家属院的投入和管理，使其成为文明、安全、美观、幽静的乐园，并逐 步增加文化和文明的含量，进一步丰富居民群众的业余文化生活，结合“四城联创”活动的深入开展，大搞爱国卫生运动，倡导文明行为，使公民道德深入人心。继续坚持“以人为本、服务居民、共驻共建、资源共享”的指导思想，发动辖区广大居民和企事业单位，共同参与文明社区建设，努力把我社区建设成为特色鲜明、环境优美、居民满意的新型社区。\n\n"
            }
            R.id.tvLocation -> {
                sendPath = "$strPath/地理位置"
                sendTitle = "地理位置"
                sendContent =
                    "\t英协社区成立于2003年12月底，位于郑州市郑汴路138号，社区东临建业路，西靠英协路，北依熊耳河，南至郑汴路，面积0.21平方公里。"
            }
            R.id.tvArea -> {
                sendPath = "$strPath/行政区划"
                sendTitle = "行政区划"
                sendContent = "\t金水区政府在城区设立17个街道办事处，作为派出机关，对所管辖区的行政和社会事务进行管理。\n" +
                        "\n" +
                        "\t17个街道办事处：文化路街道办事处、南阳新村街道办事处、经八路街道办事处、大石桥街道办事处、人民路街道办事处、花园路街道办事处、杜岭街道办事处、南阳路街道办事处、丰产路街道办事处、北林路街道办事处、未来路街道办事处、东风路街道办事处、兴达路街道办事处、国基路街道办事处、凤凰台街道办事处、杨金路街道办事处、丰庆路街道办事处。"
            }
            R.id.tvEnergy -> {
                sendPath = "$strPath/资源设施"
                sendTitle = "资源设施"
                sendContent =
                    "\t【土地资源】金水区总面积136.66平方公里，主要分为：（1）耕地1327.3公顷，占全区土地面积的0.097%。在耕地面积中，有灌溉水田17公顷，水浇地1051.4公顷，旱地258.9公顷。（2）园地225.5公顷，占全区土地面积的0.017%，全部为果园。（3）林地409.3公顷，占全区土地面积的0.03%。其中有林地260.9公顷，其他林地148.4公顷。（4）草地68.95公顷，占全区土地面积的0.005%，全部为其他草地。" +
                            "（5）城镇村及工矿用地8823.4公顷，占全区土地面积的65%。其中城市用地7066公顷，建制镇457公顷，村庄1017.9公顷，采矿用地0.9公顷，风景名胜及特殊用地281.6公顷。（6）交通运输用地999.3公顷，占全区土地面积的0.073%。其中铁路用地238.75公顷，公路用地648.8公顷，农村道路111.75公顷。（7）水域及水利设施用地1791.6公顷，占全区土地面积的13.1%。其中河流水面452.5公顷，坑塘水面1099.59公顷，内陆滩涂28.5公顷，沟渠183.1公顷，水工建筑用地28公顷。（8）其他土地20.72公顷，占全区土地总面积的0.002%。其中设施农用地面积20.39公顷，田坎0.33公顷。\n" +
                            "\n" +
                            "\t【水资源】 金水区水资源丰富，境内共有7条河流。其中，黄河：流经龙子湖街道办事处的马渡、来潼寨、三坝等村，入中牟县境，境内河段长7公里。贾鲁河：境内长度23公里，流域面积63平方公里。东风渠：境内长度25公里，流域面积37平方公里。金水河：境内长度8.64公里，流域面积22平方公里。熊儿河：境内长度8.1公里，流域面积31平方公里。七里河：境内长9.2公里，流域面积16平方公里。贾鲁支河：境内长度25公里，流域面积77平方公里。金水辖区水资源总量为31330万立方米, 人均水量450.7立方米，每公顷农田平均用水量为12750立方米。年平均利用黄河水2523万立方米。地表水年径流深为90毫米,年径流量为2179.8万立方米。地下水以浅层水为主,埋藏深度0.5米～10米,年允许开采量20万立方米/平方公里～50万立方米/平方公里。单井出水量约50吨/小时。全年黄河侧渗补给量700万立方米。全区地下水年允许开采量13876万立方米。\n" +
                            "\n" +
                            "\t【生物资源】 金水区生物类繁多，在农业生产中利用价值较高，并呈现养殖种类增加,农业役用大牲畜数量明显减少的趋势。养殖动物主要有:黄牛、水牛、奶牛、马、骡、驴、山羊、绵羊、猪、犬、鸡、鸭、鹅、鸽等；特种养殖动物有:海狸鼠、珍珠鸡、鹊辑、肉鸽、梅花鹿、小香猪、北京填鸭、小尾寒羊等；奶牛养殖业发展较为迅猛。由于辖区地处中原,靠近城市,境内缺少山、河、湖泊,野生动物资源相对稀少，境内存野生动物主要有:鸟类、鱼类飞鼠类及刺猜、黄鼠狼(黄刷)、野兔、野鸡等；鸟类主要有喜鹊、斑坞、麻雀飞布谷鸟、乌鸦、莺鸟、大雁、猫头鹰、燕子、黄莺、啄木鸟等。境内栽培植物主要有粮食、油料、蔬菜、林木、果树、花卉、药材7大类。其中粮食作物以小麦、水稻、玉米、大豆、绿豆、红薯等为主。\n" +
                            "\n"
            }

        }

        intent.putExtra("path", sendPath)
        intent.putExtra("title", sendTitle)
        intent.putExtra("content", sendContent)
        startActivity(intent)
    }
}