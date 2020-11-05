package org.firespoon.fsbotserver.service;

import org.firespoon.fsbotserver.exception.PropertyNotFoundException;
import org.firespoon.fsbotserver.mapper.CurrentCardMapper;
import org.firespoon.fsbotserver.model.Card;
import org.firespoon.fsbotserver.model.CheckResult;
import org.firespoon.fsbotserver.model.CurrentCard;
import org.firespoon.fsbotserver.model.DiceResult;
import org.firespoon.fsbotserver.utils.Ensure;
import org.firespoon.fsbotserver.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class CocService {
    private static final Map<String, Integer> defaultProperties = new LinkedHashMap<>();

    static {
        defaultProperties.put("会计", 5);
        defaultProperties.put("表演", 5);
        defaultProperties.put("动物驯养", 1);
        defaultProperties.put("人类学", 5);
        defaultProperties.put("估价", 5);
        defaultProperties.put("考古学", 1);
        defaultProperties.put("炮术", 1);
        defaultProperties.put("天文学", 1);
        defaultProperties.put("斧", 15);
        defaultProperties.put("生物学", 1);
        defaultProperties.put("植物学", 1);
        defaultProperties.put("弓", 15);
        defaultProperties.put("斗殴", 25);
        defaultProperties.put("链锯", 10);
        defaultProperties.put("魅惑", 15);
        defaultProperties.put("化学", 1);
        defaultProperties.put("攀爬", 20);
        defaultProperties.put("计算机使用", 5);
        defaultProperties.put("信用评级", 0);
        defaultProperties.put("密码学", 1);
        defaultProperties.put("克苏鲁神话", 0);
        defaultProperties.put("爆破", 1);
        defaultProperties.put("乔装", 5);
        defaultProperties.put("潜水", 1);
        defaultProperties.put("汽车驾驶", 20);
        defaultProperties.put("电气维修", 10);
        defaultProperties.put("电子学", 1);
        defaultProperties.put("工程学", 1);
        defaultProperties.put("话术", 5);
        defaultProperties.put("美术", 5);
        defaultProperties.put("急救", 30);
        defaultProperties.put("连枷", 10);
        defaultProperties.put("火焰喷射器", 10);
        defaultProperties.put("司法科学", 1);
        defaultProperties.put("伪造", 5);
        defaultProperties.put("绞索", 15);
        defaultProperties.put("地质学", 1);
        defaultProperties.put("手枪", 20);
        defaultProperties.put("重武器", 10);
        defaultProperties.put("历史", 5);
        defaultProperties.put("催眠", 1);
        defaultProperties.put("恐吓", 15);
        defaultProperties.put("跳跃", 20);
        defaultProperties.put("法律", 5);
        defaultProperties.put("图书馆使用", 20);
        defaultProperties.put("聆听", 20);
        defaultProperties.put("锁匠", 1);
        defaultProperties.put("机枪", 10);
        defaultProperties.put("数学", 10);
        defaultProperties.put("机械维修", 10);
        defaultProperties.put("医学", 1);
        defaultProperties.put("气象学", 1);
        defaultProperties.put("博物学", 10);
        defaultProperties.put("导航", 10);
        defaultProperties.put("神秘学", 5);
        defaultProperties.put("操作重型机械", 1);
        defaultProperties.put("说服", 10);
        defaultProperties.put("药学", 1);
        defaultProperties.put("摄影", 5);
        defaultProperties.put("物理", 1);
        defaultProperties.put("驾驶", 1);
        defaultProperties.put("精神分析", 1);
        defaultProperties.put("心理学", 10);
        defaultProperties.put("读唇", 1);
        defaultProperties.put("骑术", 5);
        defaultProperties.put("步枪", 25);
        defaultProperties.put("霰弹枪", 25);
        defaultProperties.put("妙手", 10);
        defaultProperties.put("矛", 20);
        defaultProperties.put("侦查", 25);
        defaultProperties.put("潜行", 20);
        defaultProperties.put("冲锋枪", 15);
        defaultProperties.put("剑", 20);
        defaultProperties.put("游泳", 20);
        defaultProperties.put("投掷", 20);
        defaultProperties.put("追踪", 10);
        defaultProperties.put("鞭", 5);
        defaultProperties.put("动物学", 1);
    }

    private final CurrentCardMapper mapper;
    private final CardFeignService cardFeignService;
    private final DiceFeignService diceFeignService;

    @Autowired
    public CocService(
            CurrentCardMapper mapper,
            CardFeignService cardFeignService,
            DiceFeignService diceFeignService
    ) {
        this.mapper = mapper;
        this.cardFeignService = cardFeignService;
        this.diceFeignService = diceFeignService;
    }

    public CurrentCard newCurrentCard(Long placeId, Long ownerId) {
        CurrentCard record = new CurrentCard();
        record.setPlaceId(placeId);
        record.setOwnerId(ownerId);
        record.setProperties(defaultProperties);
        Ensure.ensure(mapper.insert(record) > 0, "插入失败");
        return MapperUtils.unionSelect(mapper, record);
    }

    public Integer setCurrentCard(Long placeId, Long ownerId, Card card) {
        if (card == null) {
            return 0;
        }
        CurrentCard currentCard = getCurrentCard(placeId, ownerId);
        if (currentCard == null) {
            currentCard = new CurrentCard(card, placeId);
        }
        currentCard.setProperties(card.getProperties());
        return mapper.update(currentCard);
    }

    public CurrentCard getCurrentCard(Long placeId, Long ownerId) {
        CurrentCard param = new CurrentCard();
        param.setPlaceId(placeId);
        param.setOwnerId(ownerId);
        CurrentCard res = MapperUtils.unionSelect(mapper, param);
        if (res == null) {
            res = newCurrentCard(placeId, ownerId);
        }
        return res;
    }

    public Integer saveCard(
            Long placeId,
            Long ownerId,
            String name
    ) {
        CurrentCard currentCard = getCurrentCard(placeId, ownerId);
        Card card = new Card(currentCard, name);
        return cardFeignService.save(card).getData();
    }

    public Integer loadCard(
            Long placeId,
            Long ownerId,
            String name
    ) {
        Card card = cardFeignService.load(ownerId, name).getData();
        if (card == null) {
            return 0;
        }
        return setCurrentCard(placeId, ownerId, card);
    }

    public Integer getProperty(
            Long placeId,
            Long ownerId,
            String name
    ) {
        CurrentCard currentCard = getCurrentCard(placeId, ownerId);
        Map<String, Integer> properties = currentCard.getProperties();
        return properties.get(name);
    }

    public Integer setProperty(
            Long placeId,
            Long ownerId,
            String name,
            Integer value
    ) {
        CurrentCard currentCard = getCurrentCard(placeId, ownerId);
        Map<String, Integer> properties = currentCard.getProperties();
        properties.put(name, value);
        return mapper.update(currentCard);
    }

    public Integer init(
            Long placeId,
            Long ownerId
    ) {
        CurrentCard currentCard = getCurrentCard(placeId, ownerId);
        currentCard.setProperties(defaultProperties);
        return mapper.update(currentCard);
    }

    public CheckResult check(
            Long placeId,
            Long ownerId,
            String name,
            Integer value
    ) {
        Integer property = value;
        if (property == null) {
            property = getProperty(placeId, ownerId, name);
        }

        Ensure.ensure(property != null, "未找到技能或属性");

        DiceResult diceResult = diceFeignService.dice("1d100").getData();
        Integer diceRes = diceResult.getRes();

        CheckResult checkResult = new CheckResult();
        checkResult.setDiceResult(diceResult);
        checkResult.setProperty(property);
        if (1 <= diceRes && diceRes <= 5) {
            checkResult.setResult("大成功");
        } else if (diceRes <= property / 5) {
            checkResult.setResult("极难成功");
        } else if (diceRes <= property / 2) {
            checkResult.setResult("困难成功");
        } else if (diceRes <= property) {
            checkResult.setResult("成功");
        } else if(diceRes > 95) {
            checkResult.setResult("大失败");
        } else {
            checkResult.setResult("失败");
        }

        return checkResult;
    }
}
