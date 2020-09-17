package comp1110.ass2;

import java.util.*;

public class Games {
  public int number;
  public String objective;
  String placement;
  Set<String> placements;

  Games(int iNumber, String iObjective, String iPlacement) {
    number = iNumber;
    objective = iObjective;
    placement = iPlacement;
    placements = new HashSet<>();
    placements.add(iPlacement);
  }

  /**
   * Returns the iPlacement string from this class
   * @param iNumber Game number from 1 to 120
   * @return Game iPlacement string
   *
   * Code written by Jiwon Sin
   */
  public static String getPlacement (int iNumber) {
    return SOLUTIONS[iNumber].objective;
  }

  public static final Games[] SOLUTIONS = {
          new Games(1, "B03SG70Si52SL00Nn01Er41WS40Ny62N", "B03SG70Si52SL00Nn01Eo63Sp20Er41WS40Ny62N"),
          new Games(2, "G00WI10Nl02SN82Eo31Sr40Ns52Sy43S", "b03SG00WI10Nl02SN82Eo31SP60Sr40Ns52Sy43S"),
          new Games(3, "G11SI73Sl43Sn32So80Ep50Ns03SY51S", "b00WG11SI73Sl43Sn32So80Ep50NR10Ns03SY51S"),
          new Games(4, "B22Ng62NI40Sl23SN11Wr01Ws60Ny00N", "B22Ng62NI40Sl23SN11WO81Ep53Sr01Ws60Ny00N"),
          new Games(5, "b03Sg31SL82Eo40NP60Sr00NS52Sy43S", "b03Sg31SI02SL82En11No40NP60Sr00NS52Sy43S"),
          new Games(6, "G70SI03SL21En01So63Sr00Ns51Wy31E", "B62NG70SI03SL21En01So63Sp40Nr00Ns51Wy31E"),
          new Games(7, "b43Sg42So80EP03SR00WS20NY11S", "b43Sg42Si70Wl82Wn50Eo80EP03SR00WS20NY11S"),
          new Games(8, "i73SL12En00NP33SR80Es01Wy40W", "b51SG50Si73SL12En00No20EP33SR80Es01Wy40W"),
          new Games(9, "b40Nl30WO01Wp51Nr63SS23SY52S", "b40Ng00Ni11El30WN80EO01Wp51Nr63SS23SY52S"),
          new Games(10, "B12Ng02WI82EL62En71No51Ws13S", "B12Ng02WI82EL62En71No51Wp20Nr60Ns13SY00S"),
          new Games(11, "B00Ng81Ei61El50Wn11SP01Wy30E", "B00Ng81Ei61El50Wn11SO60NP01WR23Ss63Sy30E"),
          new Games(12, "B12Ng43SL70En40Ep60WS10Ny80E", "B12Ng43Si73SL70En40EO00Wp60Wr03SS10Ny80E"),
          new Games(13, "b20NI63No81EP42Nr31NS60Ny33S", "b20NG00EI63Nl02Wn12Eo81EP42Nr31NS60Ny33S"),
          new Games(14, "g00NI80En42NP21Ns13Ny30N", "b33Sg00NI80El73Sn42NO01WP21NR70Ws13Ny30N"),
          new Games(15, "B00Ni41EL72No01Wp11Sr40NS13S", "B00NG70Si41EL72Nn73So01Wp11Sr40NS13Sy51E"),
          new Games(16, "g73SI40Nl41Wo00Np80Es01WY70W", "B11Eg73SI40Nl41WN52Eo00Np80Er31Ws01WY70W"),
          new Games(17, "b50Ng73Si43Sn11EO00Wr03SY10N", "b50Ng73Si43SL61En11EO00Wp32Nr03SS80EY10N"),
          new Games(18, "g13Si73Sl43Sn30So80Ep00NS50N", "B01Wg13Si73Sl43Sn30So80Ep00NR52NS50NY12N"),
          new Games(19, "g00NI30SL71En50Ns23Sy63S", "B01Sg00NI30SL71En50No42NP03Nr80Es23Sy63S"),
          new Games(20, "b80EG21Wi50Wl00Wn73Sp51E", "b80EG21Wi50Wl00Wn73SO10Np51Er41WS70WY03S"),
          new Games(21, "b03Sg21WI73Nl70Sn53Sr50N", "b03Sg21WI73Nl70Sn53SO10NP00Wr50NS41Wy61S"),
          new Games(22, "B51Sg02Nl32WN03Sr60Ns53S", "B51Sg02Ni42Sl32WN03SO81Ep20Nr60Ns53SY00S"),
          new Games(23, "b43Sn61WO50NR22Ss80Ey03S", "b43SG01EI30El82Wn61WO50Np00NR22Ss80Ey03S"),
          new Games(24, "b20Ni80Wn51Wp81Er31ES53S", "b20NG60Ei80WL12Nn51Wo03Sp81Er31ES53SY00W"),
          new Games(25, "b81EG00WL40Eo33NP53SY03S", "b81EG00Wi61SL40En30Wo33NP53Sr10Es60NY03S"),
          new Games(26, "g70NI10SL52Ep81Er00Ws03S", "b11Sg70NI10SL52En33NO71Wp81Er00Ws03SY30N"),
          new Games(27, "b81Eg03SN72Eo61WP00NY41E", "b81Eg03Si40WL02NN72Eo61WP00Nr21Es60NY41E"),
          new Games(28, "B81EI50El43Sn03So23NP11S", "B81Eg70NI50El43Sn03So23NP11Sr20Ns71WY00W"),
          new Games(29, "i00Nn60So30Np31Wr80EY41E", "B01WG62Ni00NL12En60So30Np31Wr80Es63SY41E"),
          new Games(30, "b40Wg73SN52EP01Ws00NY60N", "b40Wg73Si50SL72NN52Eo11EP01Wr31Ws00NY60N"),
          new Games(31, "b60Ni63SO81EP01Ws00NY23S", "b60NG11Si63Sl50Wn71WO81EP01Wr40Ws00NY23S"),
          new Games(32, "b81EG31Wl02WN72Es60NY20N", "b81EG31Wi42El02WN72Eo61WP00Er21Ws60NY20N"),
          new Games(33, "b30Ng41WI00NN12NP80Es63S", "b30Ng41WI00NL51EN12No70WP80Er01Ws63Sy23S"),
          new Games(34, "I23Nl73Sn03So00Wr61SS60N", "b10Ng40SI23Nl73Sn03So00WP53Nr61SS60NY21S"),
          new Games(35, "b00Wg40EI33NL80Eo03Sr63S", "b00Wg40EI33NL80EN71Wo03SP10Nr63Ss11Sy60W"),
          new Games(36, "g33SI00Nn51Wo01Wr81W", "b80Eg33SI00NL12En51Wo01Wp40Nr81WS30WY61E"),
          new Games(37, "g33NI40Nn82Ep03Sr00N", "b51Sg33NI40NL70Nn82EO02Np03Sr00NS53Sy11N"),
          new Games(38, "G40EP01Wr23Ss61Wy00N", "b81EG40EI11Sl22SN72Eo60NP01Wr23Ss61Wy00N"),
          new Games(39, "B00Ni82EN50WP03Ss43S", "B00Ng11Si82El01WN50Wo60EP03Sr40Ws43SY80W"),
          new Games(40, "I61WN21No00Nr40EY60N", "b13SG82EI61Wl12SN21No00Np01Wr40Es53SY60N"),
          new Games(41, "l00WN30Wr61Ws23SY81E", "b60EG10Ei80Wl00WN30Wo40EP03Nr61Ws23SY81E"),
          new Games(42, "B42Ng03Si80Wl50Sn32W", "B42Ng03Si80Wl50Sn32Wo43SP30Nr10ES00WY81E"),
          new Games(43, "n00NP01Wr30NS33Sy41N", "b32Ng63NI70NL82En00No11EP01Wr30NS33Sy41N"),
          new Games(44, "G31Si00Wo20NP81Er60N", "b21WG31Si00Wl63Sn61Eo20NP81Er60NS33NY01E"),
          new Games(45, "b30NN70WP23Ss80EY00W", "b30NG31Si02El61WN70Wo63SP23Sr20Ws80EY00W"),
          new Games(46, "o20Sr50ES70WY22N", "b00Ng01Ei33Sl80EN03So20SP63Sr50ES70WY22N"),
          new Games(47, "b63So43NS11EY41S", "b63Sg81Wi40Nl02WN70No43NP00Nr31WS11EY41S"),
          new Games(48, "G82Eo00Wp32Ny60N", "b03SG82Ei11El10NN43So00Wp32NR71WS30Sy60N"),
          new Games(49, "b41Wg81WY01W", "b41Wg81WI13SL70Nn21EO63Sp00Nr40Es60WY01W"),
          new Games(50, "b52Nl00WN22No60N", "b52Ng63NI03Sl00WN22No60NP10NR81Es30Sy33S"),
          new Games(51, "b10Nl00Wn32NS60N", "b10NG62Ni11El00Wn32No30Sp43SR03SS60NY81E"),
          new Games(52, "b41NG52Ey01S", "b41NG52EI00Nl72Wn70No23SP03Nr30NS81Ey01S"),
          new Games(53, "l00Wn30Nr80ES43S", "b81WG50SI03Sl00Wn30No31WP10Er80ES43Sy42N"),
          new Games(54, "g00WL80Eo23SP03Nr63SY52S", "b11Sg00WI10NL80En61No23SP03Nr63Ss40NY52S"),
          new Games(55, "B11Eo81Er53SY31E", "B11EG00Ni30WL61En02Wo81Ep60Nr53Ss50WY31E"),
          new Games(56, "b63SG61Nl42WS01W", "b63SG61Ni12El42WN80Eo00NP40Nr21ES01Wy52S"),
          new Games(57, "g73Sl52WP13S", "B01Wg73Si40Wl52Wn12No50EP13Sr80ES70WY00N"),
          new Games(58, "g41Ei82En11So43S", "B00Ng41Ei82EL40Nn11So43Sp60Er01WS13NY80W"),
          new Games(59, "r31Ns42NY33S", "b20NG00EI63Nl02Wn12Eo60NP81Er31Ns42NY33S"),
          new Games(60, "g31EN21Ws61S", "B60Ng31EI73Nl00WN21Wo43Sp50Wr10Ns61SY03S"),
          new Games(61, "b63Si41Nn11S", "b63Sg00Ni41NL80En11SO70WP01Wr30NS32Sy23S"),
          new Games(62, "i11Ss52Ny41W", "B53SG20Ni11Sl70Sn82Eo50NP03SR00Ws52Ny41W"),
          new Games(63, "i12El43SN82E", "b21Eg72Wi12El43SN82Eo00NP51NR40WS01Wy60N"),
          new Games(64, "G00EI71Wn02Wr80Es63S", "b41EG00EI71WL22Nn02Wo13SP20Nr80Es63Sy60W"),
          new Games(65, "G00WN61Sp10Ns31E", "b50WG00Wi11El53SN61So60Np10NR03Ss31EY81E"),
          new Games(66, "G03Sl41So11N", "B00WG03Si32Sl41SN72Wo11Np81Er33SS60Ny20N"),
          new Games(67, "b81EO11Sr52N", "b81EG13Si63NL30Nn00NO11SP60Nr52Ns01Wy43S"),
          new Games(68, "g53Ni33Ss32N", "B01Wg53Ni33SL12En73SO80Ep51Nr40Ns32NY00N"),
          new Games(69, "i80Ey01W", "B50Ng30Ei80El71WN12NO13Sp00Nr63SS51Wy01W"),
          new Games(70, "i03Sn32WS43S", "b80EG00Wi03Sl82Wn32Wo50SP30NR10ES43Sy42N"),
          new Games(71, "b53Si71Wl82En41S", "b53SG11Si71Wl82En41So33Np20NR60NS03SY00W"),
          new Games(72, "b43SG82WR11S", "b43SG82Wi70Wl32Sn80Eo50EP20NR11SS03Sy00W"),
          new Games(73, "n11Sp13SS53S", "B01Wg61SI40Wl32Sn11SO81Ep13Sr60NS53Sy00N"),
          new Games(74, "b81EI21Es53S", "b81EG10NI21EL62Nn03So00WP60Nr40Es53Sy31E"),
          new Games(75, "g73Si41Sn03Sp80E", "B00Wg73Si41SL61En03So10Ep80ER50NS33Sy30W"),
          new Games(76, "b80EG51Wl00WP03N", "b80EG51Wi21Sl00WN10NO40NP03Nr81Ws23Sy61E"),
          new Games(77, "b01WG23Ni42En71W", "b01WG23Ni42El62Wn71WO60NP20Sr12Ns00NY81E"),
          new Games(78, "G00EO20Nr80Ey60W", "b23SG00EI71Wl02WN31SO20Np12Sr80Es63Sy60W"),
          new Games(79, "I03Sl33Sn01S", "B80EG22SI03Sl33Sn01So20Sp52Nr00NS50Ny63S"),
          new Games(80, "l51Wn61Sp12N", "b00Wg30NI10Sl51Wn61SO60Np12NR43Ss03SY81E"),
          new Games(81, "p80ER23SS22N", "b00NG61Wi11Wl02Wn30So63Sp80ER23SS22NY50N"),
          new Games(82, "B20ER42N", "B20EG00Ni40Sl82Wn02No80Ep50NR42NS43Sy03S"),
          new Games(83, "I42Ns50N", "B23Sg30SI42Nl82WN12No80Ep00NR01Ws50Ny61E"),
          new Games(84, "g21Np63SY03S", "B41Eg21Ni22Sl00WN62No80Ep63Sr10NS50NY03S"),
          new Games(85, "B31En61Wr51W", "B31Eg03SI73Sl21Wn61WO10Np80Er51Ws00WY50N"),
          new Games(86, "g30Nn12S", "B31Sg30NI00Nl71Wn12SO60Np01Wr13Ss81EY53S"),
          new Games(87, "n03Ss23Ny41S", "B20NG60NI00Sl82Wn03So80EP43Sr02Ns23Ny41S"),
          new Games(88, "g12NI03SY33S", "B81Eg12NI03Sl30Sn00WO60Np53Nr41Ss10NY33S"),
          new Games(89, "i43Sl73Sn03S", "B00WG70Ni43Sl73Sn03So10Ep31WR61SS30Ny52S"),
          new Games(90, "L71Ey00W", "b20Ng11Wi82EL71EN31Wo03Sp41SR60NS43Sy00W"),
          new Games(91, "G73Sn62NR33S", "b60NG73Si71Nl03Sn62NO00WP11SR33Ss10Ny40E"),
          new Games(92, "g50En11E", "B43Sg50Ei32Nl82Wn11EO03Sp70Wr80ES20NY00W"),
          new Games(93, "N31Wo50E", "B03Sg11Wi00WL42EN31Wo50Ep80Er20Ns63SY70W"),
          new Games(94, "b42So10N", "b42SG50Ei02EL70Nn81Wo10Np00Wr63SS21SY23S"),
          new Games(95, "G41Ny30W", "B00WG41Ni50Nl61Sn10Eo63SP43Nr80ES03Sy30W"),
          new Games(96, "g71En21Ny03S", "B60Ng71Ei82El00Wn21NO12SP43Sr10NS50Wy03S"),
          new Games(97, "g60Sl41W", "b01Wg60SI00Nl41WN12NO30Np63SR80Es52Ny23S"),
          new Games(98, "i22Ns50W", "b00Ng01Ei22Nl20SN03SO60Np71WR33Ss50WY81E"),
          new Games(99, "G73Sl02Ws33S", "B70WG73SI40Nl02Wn80Eo13NP11Nr00Ns33Sy32N"),
          new Games(100, "g13Sn73So01W", "b30Ng13SI00Nl70Wn73So01WP80ER41Es61WY12N"),
          new Games(101, "b33Sp30S", "b33SG80Ei50Nl02WN73SO00Np30SR52Ns13Ny11S"),
          new Games(102, "G52Nl21S", "b01WG52Ni61Nl21SN11Wo40Np63SR80ES23Sy00N"),
          new Games(103, "R63SY10S", "b31Sg03Si22Sl33SN80EO70Wp30NR63Ss00WY10S"),
          new Games(104, "n42Sy11S", "B60NG13SI82El61Sn42So01Wp00Nr43SS30Sy11S"),
          new Games(105, "b63Sn03Sy31S", "b63SG80EI33Sl50Sn03So30NP52Sr10ES00Wy31S"),
          new Games(106, "i73SL62Np33S", "b40EG60Ni73SL62Nn13NO11Sp33SR01Ws80Ey00N"),
          new Games(107, "L52No30S", "B60NG03Si33SL52Nn81Wo30Sp00WR63Ss10Ny12N"),
          new Games(108, "L22Np10S", "B00WG51Wi33SL22Nn03So63Sp10Sr80ES70Wy30N"),
          new Games(109, "l63So81Ep11S", "B00Ng70NI61El63SN40No81Ep11Sr01WS13Ny41E"),
          new Games(110, "G50Ei03S", "B63SG50Ei03Sl20SN42Eo00Np13Nr80ES70Wy01S"),
          new Games(111, "G02WO30N", "b80EG02Wi62NL00Nn60SO30Np63SR11Ss32Sy23S"),
          new Games(112, "g01WN51W", "b41Wg01WI21Wl61SN51WO03Sp63SR80Es00Ny40N"),
          new Games(113, "g43NN30N", "B01Wg43Ni00NL60NN30No51Sp63Sr11SS13Sy80E"),
          new Games(114, "G00Ni80Wn50NS20S", "B03SG00Ni80Wl51Sn50No02Np32Sr43SS20SY81E"),
          new Games(115, "G41Ni50N", "B00WG41Ni50Nl61Sn10Eo63SP43Nr80ES03Sy30W"),
          new Games(116, "g10EL00Wn62N", "B63Sg10EI03SL00Wn62No80Ep21Er30ES50Ny41E"),
          new Games(117, "n33SS30N", "b00Wg60SI10Sl03Sn33SO52Np12NR80ES30Ny63S"),
          new Games(118, "B03SN43S", "B03Sg51Wi00Wl82WN43SO12Np40Nr80ES10Sy71W"),
          new Games(119, "g20SS43S", "B12Ng20Si82El01Wn00No40NP60Sr03SS43SY52N"),
          new Games(120, "l21WY51S", "B00NG53Ni30El21Wn73So80Ep50Nr13SS01WY51S")
  };
}
